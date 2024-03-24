package com.plano.saude.cadastro.dto;

import java.time.LocalDate;
import java.util.Set;

//Sugestão: usar lombok
public class BeneficiarioDTO {
	private Long id;
	private String nome;
	private String telefone;
	private LocalDate dataNascimento;
	private LocalDate dataInclusao;
	private LocalDate dataAtualizacao;
	private Set<DocumentoDTO> documentos;

	public BeneficiarioDTO(Long id, String nome, String telefone, LocalDate dataNascimento, LocalDate dataInclusao,
			LocalDate dataAtualizacao, Set<DocumentoDTO> documentos) {
		this(nome, telefone, dataNascimento, dataInclusao, dataAtualizacao, documentos);
		this.id = id;
	}

	public BeneficiarioDTO(String nome, String telefone, LocalDate dataNascimento, LocalDate dataInclusao,
			LocalDate dataAtualizacao, Set<DocumentoDTO> documentos) {
		this.nome = nome;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.dataInclusao = dataInclusao;
		this.dataAtualizacao = dataAtualizacao;
		this.documentos = documentos;
	}	
	
	public BeneficiarioDTO() {} 

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public LocalDate getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(LocalDate dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public LocalDate getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDate dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Set<DocumentoDTO> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(Set<DocumentoDTO> documentos) {
		this.documentos = documentos;
	}

}

