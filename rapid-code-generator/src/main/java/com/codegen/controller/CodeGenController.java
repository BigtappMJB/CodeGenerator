package com.codegen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codegen.exception.RapidControllerException;
import com.codegen.model.GeneratorInput;
import com.codegen.service.CodeGeneratorService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/generator")
public class CodeGenController {

	
    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @PostMapping("/generateApp")
    public ResponseEntity<String> generateFullApp(@RequestBody @Valid GeneratorInput input) {
    	 log.info("Received request to generate Spring Boot app for class: {}", input.getClassName());
        if (input.getClassName() == null || input.getClassName().isEmpty()) {
        	 log.warn("Invalid input: input or className is null");
            throw new RapidControllerException("Class name must not be null or empty");
        }
        String message = codeGeneratorService.generateFullSpringBootApp(input);
        log.info("Application generation response: {}", message);
        return ResponseEntity.ok(message);
    }
}

