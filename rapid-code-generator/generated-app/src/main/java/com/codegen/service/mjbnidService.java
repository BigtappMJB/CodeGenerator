package com.codegen;

import com.codegen.mjbnid;
import java.util.Optional;
import java.util.List;
import org.springframework.stereotype.Service;
import com.codegen.mjbnidRepository;

@Service
public class mjbnidService {

    private final mjbnidRepository mjbnidRepository;

    public mjbnidService(mjbnidRepository mjbnidRepository) {
        this.mjbnidRepository = mjbnidRepository;
    }

    public List<mjbnid> getAllmjbnid() {
        return mjbnidRepository.findAll();
    }

    public mjbnid save(mjbnid mjbnid) {
        return mjbnidRepository.save(mjbnid);
    }

    public void deleteById(Long id) {
        mjbnidRepository.deleteById(id);
    }

    public Optional<mjbnid> findById(Long id) {
        return mjbnidRepository.findById(id);
    }

    public void deleteAll() {
        mjbnidRepository.deleteAll();
    }
}
