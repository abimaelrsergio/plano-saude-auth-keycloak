package com.plano.saude.cadastro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plano.saude.cadastro.model.Documento;
import com.plano.saude.cadastro.repository.DocumentoRepository;

@Service
public class DocumentoService {

	@Autowired
	private DocumentoRepository documentoRepository;

	@Transactional(rollbackFor = Exception.class)
	public List<Documento> save(List<Documento> documentos) {
		return documentoRepository.saveAll(documentos);
	}
}

