package com.university.cd.first_project.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api")
public class HelloController {

    // Basic endpoint
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot with Java 25! 🚀";
    }

    @PostMapping("/echo")
    public String echo(@RequestBody Map<String, String> body) {
        return "You said: " + body.get("message");
    }

    @GetMapping("/hello/{name}")
    public String greet(@PathVariable String name) {
        return "Hello " + name;
    }
}

