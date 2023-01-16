package com.attornatus.api.backattornatus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attornatus.api.backattornatus.exceptions.NotFoundException;
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

    @PostMapping("/adicionar")
    public ResponseEntity<Pessoa> addPessoa(@RequestBody Pessoa pessoa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaRepository.save(pessoa));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Pessoa> editarPessoa(@PathVariable(value = "id") Long pessoaId, @RequestBody Pessoa pessoaAtual) {

        Pessoa pessoa = pessoaRepository.findById(pessoaId).get();
        pessoa.setNome(pessoaAtual.getNome());
        pessoa.setDataNascimento(pessoaAtual.getDataNascimento());
        pessoa.setEnderecos(pessoaAtual.getEnderecos());

        return ResponseEntity.ok(pessoaRepository.save(pessoa));
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<Pessoa> consultarPessoa(@PathVariable(value = "id") Long pessoaId) throws NotFoundException {
        Pessoa pessoa = pessoaRepository.findById(pessoaId).orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
        return ResponseEntity.ok().body(pessoa);
    }
}
