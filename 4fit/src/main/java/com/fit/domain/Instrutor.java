package com.fit.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fit.domain.dtos.InstrutorDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Instrutor extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@OneToMany
	private List<Aluno> alunos = new ArrayList<>();


	public Instrutor() {

	}

	public Instrutor(Integer id, String nome, String cpf,String dataNascimento, String telefone, String email) {
		super(id, nome, cpf, dataNascimento, telefone, email);

	}
	public Instrutor(InstrutorDTO obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.dataNascimento = obj.getDataNascimento();
		this.telefone = obj.getTelefone();
		this.email = obj.getEmail();
		this.dataCriacao = obj.getDataCriacao();
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
}