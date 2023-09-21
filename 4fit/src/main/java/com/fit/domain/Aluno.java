package com.fit.domain;

import com.fit.domain.dtos.AlunoDTO;
import jakarta.persistence.Entity;

import java.io.Serializable;

@Entity
public class Aluno extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;


    public Aluno() {
        super();
    }

    public Aluno(Integer id, String nome, String cpf, String dataNascimento, String telefone, String email) {
        super(id, nome, cpf, dataNascimento, telefone, email);

    }

    public Aluno(AlunoDTO obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.dataNascimento = obj.getDataNascimento();
        this.telefone = obj.getTelefone();
        this.email = obj.getEmail();
        this.dataCriacao = obj.getDataCriacao();

    }





}