
package com.codegen.controller;

import com.codegen.model.Juned;
import com.codegen.service.JunedService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/juned")
public class JunedController {

    private final JunedService junedService;

    public JunedController(JunedService junedService) {
        this.junedService = junedService;
    }

    @GetMapping("/all")
    public List<Juned> getAll() {
        return junedService.getAllJuned();
    }

    @PostMapping("/create")
    public Juned create(@RequestBody Juned juned) {
        return junedService.save(juned);
    }

    @GetMapping("/find/{id}")
    public Optional<Juned> getById(@PathVariable Long id) {
        return junedService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        junedService.deleteById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        junedService.deleteAll();
    }
}
