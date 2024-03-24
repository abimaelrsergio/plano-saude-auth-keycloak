package com.plano.saude.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plano.saude.cadastro.model.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long>{

}

