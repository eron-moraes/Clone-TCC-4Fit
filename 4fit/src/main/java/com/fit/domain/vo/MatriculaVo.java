package com.fit.domain.vo;

import com.fit.domain.Aluno;
import com.fit.domain.Fatura;
import lombok.Data;

@Data
public class MatriculaVo {

    private Integer id;

    private String titulo;

    private Aluno aluno;

    private String instrutor;

    private Fatura fatura;

}
