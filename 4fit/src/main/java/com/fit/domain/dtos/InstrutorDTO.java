package com.fit.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fit.domain.Instrutor;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.time.LocalDate;

public class InstrutorDTO  implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Integer id;
    @NotNull(message = "O campo NOME é requerido")
    protected String nome;
    @CPF
    @NotNull(message = "O campo CPF é requerido")
    protected String cpf;

    protected String dataNascimento;
    protected String telefone;
    @NotNull(message = "O campo EMAIL é requerido")
    protected String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();

    public InstrutorDTO() {
        super();
    }

    public InstrutorDTO(Instrutor obj) {
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


    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

}
