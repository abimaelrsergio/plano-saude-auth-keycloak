package com.plano.saude.cadastro.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

// Sugestão: usar lombok
@Entity
public class Beneficiario {

	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private String telefone;
	private LocalDate dataNascimento;
	private LocalDate dataInclusao;
	private LocalDate dataAtualizacao;
	@OneToMany(mappedBy = "beneficiario", cascade = CascadeType.REMOVE)
	private List<Documento> documentos;

	public Beneficiario() {
	}

	public Beneficiario(Long id, String nome, String telefone, LocalDate dataNascimento, LocalDate dataInclusao,
			LocalDate dataAtualizacao) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.dataInclusao = dataInclusao;
		this.dataAtualizacao = dataAtualizacao;
	}

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

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Beneficiario beneficiario = (Beneficiario) o;
		return Objects.equals(id, beneficiario.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}

