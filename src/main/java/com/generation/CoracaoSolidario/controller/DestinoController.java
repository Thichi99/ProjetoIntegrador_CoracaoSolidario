package com.generation.CoracaoSolidario.controller;

import com.generation.CoracaoSolidario.model.Destino;
import com.generation.CoracaoSolidario.repository.DestinoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/destinos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DestinoController {
    @Autowired
    private DestinoRepository destinoRepository;

    @GetMapping
    public ResponseEntity<List<Destino>> getAll() {
        return ResponseEntity.ok(destinoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destino> getById(@PathVariable Long id) {
        return destinoRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Destino>> getByName(@PathVariable String nome) {
        return ResponseEntity.ok(destinoRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping
    public ResponseEntity<Destino> post (@Valid @RequestBody Destino destino) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(destinoRepository.save(destino));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Destino> put(@Valid @RequestBody Destino destino) {
        return destinoRepository.findById(destino.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(destinoRepository.save(destino)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Destino> destino = destinoRepository.findById(id);

        if(destino.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        destinoRepository.deleteById(id);
    }
}
