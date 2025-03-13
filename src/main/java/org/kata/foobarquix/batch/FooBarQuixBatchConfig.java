package org.kata.foobarquix.batch;

import org.kata.foobarquix.service.FooBarQuixService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@EnableBatchProcessing
public class FooBarQuixBatchConfig extends DefaultBatchConfiguration{

    private final FooBarQuixService service;

    public FooBarQuixBatchConfig(FooBarQuixService service) {
        this.service = service;
    }

    @Bean
    public FlatFileItemReader<Integer> reader() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("number");

        FieldSetMapper<Integer> fieldSetMapper = fieldSet -> fieldSet.readInt(0);

        DefaultLineMapper<Integer> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return new FlatFileItemReaderBuilder<Integer>()
                .name("numberItemReader")
                .resource(new FileSystemResource("input/input.txt"))
                .lineMapper(lineMapper)
                .build();
    }

    @Bean
    public ItemProcessor<Integer, String> processor() {
        return str -> str + "\t\t\"" + service.transform(str) + "\"";
    }

    @Bean
    public FlatFileItemWriter<String> writer() {
        return new FlatFileItemWriterBuilder<String>()
                .name("resultItemWriter")
                .resource(new FileSystemResource("target/output.txt"))
                .lineAggregator(new PassThroughLineAggregator<>())
                .build();
    }

    @Bean
    public Step mainStep(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager,
                      ItemReader<Integer> reader,
                      ItemProcessor<Integer, String> processor,
                      ItemWriter<String> writer) {
        return new StepBuilder("step1", jobRepository)
                .<Integer, String>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job mainJob(JobRepository jobRepository, Step mainStep) {
        return new JobBuilder("job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(mainStep)
                .end()
                .build();
    }
}
