package com.attornatus.api.backattornatus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attornatus.api.backattornatus.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
