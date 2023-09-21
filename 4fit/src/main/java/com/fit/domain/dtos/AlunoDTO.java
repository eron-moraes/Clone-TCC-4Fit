package com.fit.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fit.domain.Aluno;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

public class AlunoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Integer id;
    @NotNull(message = "O campo NOME é requerido")
    protected String nome;
    @NotNull(message = "O campo CPF é requerido")
    protected String cpf;
    protected String dataNascimento;
    protected String telefone;
    @NotNull(message = "O campo EMAIL é requerido")
    protected String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();

    public AlunoDTO() {
        super();
    }

    public AlunoDTO(Aluno obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.dataNascimento = obj.getDataNascimento();
        this.telefone = obj.getTelefone();
        this.email = obj.getEmail();
        this.dataCriacao = obj.getDataCriacao();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getSenha() {
//        return senha;
//    }
//
//    public void setSenha(String senha) {
//        this.senha = senha;
//    }

//    public String getTipo() {
//        return tipo;
//    }
//
//    public void setTipo(String tipo) {
//        this.tipo = tipo;
//    }

//    public Set<Perfil> getPerfis() {
//        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
//    }
//
//    public void addPerfil(Perfil perfil) {
//        this.perfis.add(perfil.getCodigo());
//    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

	public Integer getFatura() {
		return null;
	}
}
