package com.codegen.service;

import com.codegen.model.Juned;
import java.util.Optional;
import java.util.List;
import org.springframework.stereotype.Service;
import com.codegen.repository.JunedRepository;

@Service
public class JunedService {

    private final JunedRepository junedRepository;

    public JunedService(JunedRepository junedRepository) {
        this.junedRepository = junedRepository;
    }

    public List<Juned> getAllJuned() {
        return junedRepository.findAll();
    }

    public Juned save(Juned juned) {
        return junedRepository.save(juned);
    }

    public void deleteById(Long id) {
        junedRepository.deleteById(id);
    }

    public Optional<Juned> findById(Long id) {
        return junedRepository.findById(id);
    }

    public void deleteAll() {
        junedRepository.deleteAll();
    }
}
