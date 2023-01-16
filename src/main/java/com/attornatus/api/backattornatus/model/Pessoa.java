package com.attornatus.api.backattornatus.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pessoa_id")
    private Long pessoaId;
    private String nome;
    private String dataNascimento;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("pessoa")
    private List<Endereco> enderecos;

}
