package com.generation.CoracaoSolidario.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.CoracaoSolidario.model.Doacao;
import com.generation.CoracaoSolidario.repository.DoacaoRepository;
import com.generation.CoracaoSolidario.repository.CategoriaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/doacoes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DoacaoController {
	
	@Autowired
    private DoacaoRepository doacaoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	 @GetMapping
	 public ResponseEntity<List<Doacao>> getAll() {
	   return ResponseEntity.ok(doacaoRepository.findAll());
	    }

    @GetMapping("/{id}")
    public ResponseEntity<Doacao> getById(@PathVariable Long id) {
        return doacaoRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/destino/{destino}")
    public ResponseEntity<List<Doacao>> getByDestino(@PathVariable String destino) {
        return ResponseEntity.ok(doacaoRepository.findAllByDestinoContainingIgnoreCase(destino));
    }

    @PostMapping
    public ResponseEntity<Doacao> post(@Valid @RequestBody Doacao doacao){
		if(categoriaRepository.existsById(doacao.getCategoria().getId()))
			return ResponseEntity.status(HttpStatus.CREATED)
				.body(doacaoRepository.save(doacao));
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema nao existe!", null);
	}

    @PutMapping
    public ResponseEntity<Doacao> put(@Valid @RequestBody Doacao doacao){
		if(doacaoRepository.existsById(doacao.getId())) {
			
			if(categoriaRepository.existsById(doacao.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.OK)
						.body(doacaoRepository.save(doacao));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema nao existe!", null);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Doacao> doacao = doacaoRepository.findById(id);

        if(doacao.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        doacaoRepository.deleteById(id);
    }

}
