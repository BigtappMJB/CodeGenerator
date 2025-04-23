
package com.codegen.controller;

import com.codegen.model.efjuned;
import com.codegen.service.efjunedService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/efjuned")
public class efjunedController {

    private final efjunedService efjunedService;

    public efjunedController(efjunedService efjunedService) {
        this.efjunedService = efjunedService;
    }

    @GetMapping("/all")
    public List<efjuned> getAll() {
        return efjunedService.getAllefjuned();
    }

    @PostMapping("/create")
    public efjuned create(@RequestBody efjuned efjuned) {
        return efjunedService.save(efjuned);
    }

    @GetMapping("/find/{id}")
    public Optional<efjuned> getById(@PathVariable Long id) {
        return efjunedService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        efjunedService.deleteById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        efjunedService.deleteAll();
    }
}
