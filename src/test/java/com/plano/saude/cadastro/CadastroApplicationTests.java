package com.plano.saude.cadastro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.plano.saude.cadastro.dto.BeneficiarioDTO;
import com.plano.saude.cadastro.dto.DocumentoDTO;
import com.plano.saude.cadastro.enuns.TipoDocumento;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void getBeneficiarios() {
		ResponseEntity<String> response = restTemplate
			.withBasicAuth("abimael","abimael")
			.getForEntity("/plano-saude/beneficiarios", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void shouldCreateANewBeneficiario(){
		DocumentoDTO documento = new DocumentoDTO(TipoDocumento.RG, "Registro geral", LocalDate.now(), LocalDate.now());
		Set<DocumentoDTO> documentos = new HashSet<>();
		documentos.add(documento);
		BeneficiarioDTO beneficiarioDTO = new BeneficiarioDTO("Abimael Sergio", "97070-7070", LocalDate.now(), LocalDate.now(), LocalDate.now(), documentos);
		ResponseEntity<Void> createResponse = restTemplate
			.withBasicAuth("abimael","abimael")
			.postForEntity("/plano-saude/beneficiarios", beneficiarioDTO, Void.class);
		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}

	@Test
	void shouldUpdateAnExistingBeneficiario(){
		DocumentoDTO documento = new DocumentoDTO(TipoDocumento.RG, "Registro geral", LocalDate.now(), LocalDate.now());
		Set<DocumentoDTO> documentos = new HashSet<>();
		documentos.add(documento);
		BeneficiarioDTO beneficiarioDTO = new BeneficiarioDTO("Abimael Sergio", "97070-7070", LocalDate.now(), LocalDate.now(), LocalDate.now(), documentos);
		HttpEntity<BeneficiarioDTO> request = new HttpEntity<>(beneficiarioDTO);
		ResponseEntity<Void> createResponse = restTemplate
			.withBasicAuth("abimael","abimael")
			.exchange("/plano-saude/beneficiarios/2", HttpMethod.PUT, request, Void.class);
		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}
}

