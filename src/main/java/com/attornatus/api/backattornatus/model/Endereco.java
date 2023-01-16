package com.attornatus.api.backattornatus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enderecoId;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private boolean principal;

    @ManyToOne
    @JsonIgnoreProperties("enderecos")
    private Pessoa pessoa;



}
