package com.attornatus.api.backattornatus.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "pessoa")
    private List<Endereco> enderecos;

}
