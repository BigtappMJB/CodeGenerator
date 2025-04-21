package com.codegen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codegen.model.GeneratorInput;
import com.codegen.service.CodeGeneratorService;
//import com.codegen.service.FreemarkerService;

@RestController
@RequestMapping("/api/generator")
public class CodeGenController {

	
    @Autowired
    private CodeGeneratorService codeGeneratorService;

//    @Autowired
//    private FreemarkerService freemarkerService;

  
    @PostMapping("/entity")
    public ResponseEntity<String> generateEntity(@RequestBody GeneratorInput input) {
        String generatedCode = codeGeneratorService.generateEntity(input);
        return ResponseEntity.ok(generatedCode);
    }

   
    @PostMapping("/repository")
    public ResponseEntity<String> generateRepository(@RequestBody GeneratorInput input) {
        String generatedCode = codeGeneratorService.generateRepository(input);
        return ResponseEntity.ok(generatedCode);
    }

    @PostMapping("/service")
    public ResponseEntity<String> generateService(@RequestBody GeneratorInput input) {
        String generatedCode = codeGeneratorService.generateService(input);
        return ResponseEntity.ok(generatedCode);
    }

    
    @PostMapping("/controller")
    public ResponseEntity<String> generateController(@RequestBody GeneratorInput input) {
        String generatedCode = codeGeneratorService.generateController(input);
        return ResponseEntity.ok(generatedCode);
    }

    
    @PostMapping("/generateApp")
    public ResponseEntity<String> generateFullApp(@RequestBody GeneratorInput input) {
        String message = codeGeneratorService.generateFullSpringBootApp(input);
        
        return ResponseEntity.ok(message);
    }
}
