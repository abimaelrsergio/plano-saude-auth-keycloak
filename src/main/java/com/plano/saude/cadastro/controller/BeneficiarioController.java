package com.plano.saude.cadastro.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.notFound;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.plano.saude.cadastro.dto.BeneficiarioDTO;
import com.plano.saude.cadastro.dto.DocumentoDTO;
import com.plano.saude.cadastro.model.Beneficiario;
import com.plano.saude.cadastro.model.Documento;
import com.plano.saude.cadastro.service.BeneficiarioService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/plano-saude")
@SecurityRequirement(name = "bearer-key") 
public class BeneficiarioController {

	@Autowired
	private BeneficiarioService beneficiarioService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/beneficiarios")
	private List<BeneficiarioDTO> findAll() {
		List<Beneficiario> beneficiarios = beneficiarioService.findAll();
		return beneficiarios.stream().map(beneficiario -> modelMapper.map(beneficiario, BeneficiarioDTO.class))
				.toList();
	}

	@GetMapping("/beneficiarios/{beneficiarioId}")
	private ResponseEntity<BeneficiarioDTO> findById(@PathVariable(name = "beneficiarioId") Long beneficiarioId) {
		Optional<Beneficiario> beneficiarioOptional = beneficiarioService.findById(beneficiarioId);
		return beneficiarioOptional.map(beneficiario -> modelMapper.map(beneficiario, BeneficiarioDTO.class))
				.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/beneficiarios/{beneficiarioId}/documentos")
	private ResponseEntity<List<DocumentoDTO>> findDocumentsById(
			@PathVariable(name = "beneficiarioId") Long beneficiarioId) {
		Optional<List<Documento>> beneficiarioOptional = beneficiarioService.findDocumentsById(beneficiarioId);
		if (beneficiarioOptional.isPresent()) {
			List<Documento> documentos = beneficiarioOptional.get();
			List<DocumentoDTO> dtos = documentos.stream()
					.map(documento -> modelMapper.map(documento, DocumentoDTO.class)).toList();
			return ResponseEntity.ok(dtos);
		}
		return notFound().build();
	}

	@DeleteMapping("/beneficiarios/{beneficiarioId}")
	private ResponseEntity<Void> deleteById(@PathVariable(name = "beneficiarioId") Long beneficiarioId) {
		beneficiarioService.deleteById(beneficiarioId);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/beneficiarios/{beneficiarioId}")
	public ResponseEntity<BeneficiarioDTO> update(@PathVariable(name = "beneficiarioId") Long beneficiarioId,
			@RequestBody BeneficiarioDTO beneficiarioDTO) {
		Beneficiario beneficiarioUpdated = beneficiarioService
				.save(modelMapper.map(beneficiarioDTO, Beneficiario.class));
		return new ResponseEntity<BeneficiarioDTO>(modelMapper.map(beneficiarioUpdated, BeneficiarioDTO.class), HttpStatus.NO_CONTENT);
	}

	@PostMapping("/beneficiarios")
	public ResponseEntity<Void> save(@RequestBody BeneficiarioDTO beneficiarioDTO) {
		Beneficiario beneficiarioCriado = beneficiarioService
				.save(modelMapper.map(beneficiarioDTO, Beneficiario.class));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(beneficiarioCriado.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
