package com.attornatus.api.backattornatus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.attornatus.api.backattornatus.model.Pessoa;
import com.attornatus.api.backattornatus.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa addPessoa(@RequestBody Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }
}
