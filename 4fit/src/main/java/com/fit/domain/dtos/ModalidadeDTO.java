package com.fit.domain.dtos;

import java.io.Serializable;

import com.fit.domain.Modalidade;

public class ModalidadeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String nome;
    private Long capacidadeAlunos;
    private double preco;
    
	public ModalidadeDTO() {
		super();
	}

	public ModalidadeDTO(Modalidade obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.capacidadeAlunos = obj.getCapacidadeAlunos();
		this.preco = obj.getPreco();
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

	public Long getCapacidadeAlunos() {
		return capacidadeAlunos;
	}

	public void setCapacidadeAlunos(Long capacidadeAlunos) {
		this.capacidadeAlunos = capacidadeAlunos;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
    
    

}
