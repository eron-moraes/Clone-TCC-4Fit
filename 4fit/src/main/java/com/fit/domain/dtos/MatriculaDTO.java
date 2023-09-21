package com.fit.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fit.domain.Matricula;

import java.io.Serializable;
import java.time.LocalDate;

public class MatriculaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;
    private Integer status;
    private Integer turno;
    private String titulo;
    private String observacoes;
    private Integer aluno;
    private Integer instrutor;

    private String nomeAluno;
    private String nomeInstrutor;

    public MatriculaDTO() {
        super();
    }

    public MatriculaDTO(Matricula obj) {
        this.id = obj.getId();
        this.dataAbertura = obj.getDataAbertura();
        this.dataFechamento = obj.getDataFechamento();
        this.status = obj.getStatus().getCodigo();
        this.turno = obj.getTurno().getCodigo();
        this.observacoes = obj.getObservacoes();
        this.aluno = obj.getAluno().getId();
        this.instrutor = obj.getInstrutor().getId();
        this.nomeAluno = obj.getAluno().getNome();
        this.nomeInstrutor = obj.getInstrutor().getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTurno() {
        return turno;
    }

    public void setTurno(Integer turno) {
        this.turno = turno;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Integer getAluno() {
        return aluno;
    }

    public void setAluno(Integer aluno) {
        this.aluno = aluno;
    }

    public Integer getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(Integer instrutor) {
        this.instrutor = instrutor;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getNomeInstrutor() {
        return nomeInstrutor;
    }

    public void setNomeInstrutor(String nomeInstrutor) {
        this.nomeInstrutor = nomeInstrutor;
    }
}
