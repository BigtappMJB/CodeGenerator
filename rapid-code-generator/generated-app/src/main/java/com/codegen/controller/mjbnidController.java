
package com.codegen.controller;

import com.codegen.mjbnid;
import com.codegen.mjbnidService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mjbnid")
public class mjbnidController {

    private final mjbnidService mjbnidService;

    public mjbnidController(mjbnidService mjbnidService) {
        this.mjbnidService = mjbnidService;
    }

    @GetMapping("/all")
    public List<mjbnid> getAll() {
        return mjbnidService.getAllmjbnid();
    }

    @PostMapping("/create")
    public mjbnid create(@RequestBody mjbnid mjbnid) {
        return mjbnidService.save(mjbnid);
    }

    @GetMapping("/find/{id}")
    public Optional<mjbnid> getById(@PathVariable Long id) {
        return mjbnidService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        mjbnidService.deleteById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        mjbnidService.deleteAll();
    }
}
