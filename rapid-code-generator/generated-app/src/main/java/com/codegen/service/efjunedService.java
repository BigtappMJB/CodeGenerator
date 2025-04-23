package com.codegen.service;

import com.codegen.model.efjuned;
import java.util.Optional;
import java.util.List;
import org.springframework.stereotype.Service;
import com.codegen.repository.efjunedRepository;

@Service
public class efjunedService {

    private final efjunedRepository efjunedRepository;

    public efjunedService(efjunedRepository efjunedRepository) {
        this.efjunedRepository = efjunedRepository;
    }

    public List<efjuned> getAllefjuned() {
        return efjunedRepository.findAll();
    }

    public efjuned save(efjuned efjuned) {
        return efjunedRepository.save(efjuned);
    }

    public void deleteById(Long id) {
        efjunedRepository.deleteById(id);
    }

    public Optional<efjuned> findById(Long id) {
        return efjunedRepository.findById(id);
    }

    public void deleteAll() {
        efjunedRepository.deleteAll();
    }
}
