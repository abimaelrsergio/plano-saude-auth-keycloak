package com.plano.saude.cadastro.service;

import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plano.saude.cadastro.model.Beneficiario;
import com.plano.saude.cadastro.model.Documento;
import com.plano.saude.cadastro.repository.BeneficiarioRepository;

@Service
public class BeneficiarioService {

	@Autowired
	private DocumentoService documentoService;

	@Autowired
	private BeneficiarioRepository beneficiarioRepository;

	@Transactional(readOnly = true, propagation = SUPPORTS)
	public List<Beneficiario> findAll() {
		return beneficiarioRepository.findAll();
	}

	@Transactional(readOnly = true, propagation = SUPPORTS)
	public Optional<Beneficiario> findById(Long id) {
		Optional<Beneficiario> beneficiarioOptional = beneficiarioRepository.findById(id);
		if (beneficiarioOptional.isPresent()) {
			return beneficiarioOptional;
		}
		return Optional.empty();
	}

	@Transactional(readOnly = true, propagation = SUPPORTS)
	public Optional<List<Documento>> findDocumentsById(Long id) {
		Optional<Beneficiario> beneficiarioOptional = beneficiarioRepository.findById(id);
		if (beneficiarioOptional.isPresent()) {
			return Optional.of(beneficiarioOptional.get().getDocumentos());
		}
		return Optional.empty();
	}

	@Transactional(rollbackFor = Exception.class)
	public Beneficiario save(Beneficiario beneficiario) {
		Beneficiario savedBeneficiario = beneficiarioRepository.save(beneficiario);
		beneficiario.getDocumentos().forEach(documento -> documento.setBeneficiario(savedBeneficiario));
		List<Documento> documentos = documentoService.save(beneficiario.getDocumentos());
		savedBeneficiario.setDocumentos(documentos);
		return savedBeneficiario;
	}

	@Transactional(rollbackFor = Exception.class)
	public Beneficiario deleteById(long id) {
		Optional<Beneficiario> beneficiarioOptional = findById(id);
		if (beneficiarioOptional.isPresent()) {
			Beneficiario beneficiario = beneficiarioOptional.get();
			beneficiarioRepository.deleteById(beneficiario.getId());
			return beneficiario;
		}
		return null;
	}

}

