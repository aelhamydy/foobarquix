package org.kata.foobarquix.controller;

import org.kata.foobarquix.service.FooBarQuixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FooBarQuixController {
    private final FooBarQuixService service;

    public FooBarQuixController(FooBarQuixService service) {
        this.service = service;
    }

    @GetMapping("/convert/{number}")
    public String convert(@PathVariable(name = "number") int number) {
        return service.transform(number);
    }
}