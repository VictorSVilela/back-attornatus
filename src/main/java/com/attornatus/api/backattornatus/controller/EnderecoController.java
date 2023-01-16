package com.attornatus.api.backattornatus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attornatus.api.backattornatus.model.Endereco;
import com.attornatus.api.backattornatus.repository.EnderecoRepository;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping
    public List<Endereco> listarEnderecos() {
        return enderecoRepository.findAll();
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Endereco> addEndereco(@RequestBody Endereco endereco) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoRepository.save(endereco));
    }
}
