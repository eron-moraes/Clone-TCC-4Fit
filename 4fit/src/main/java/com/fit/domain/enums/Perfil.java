package com.fit.domain.enums;

public enum Perfil {

    ADMIN(0, "ROLE_ADMIN"), COLABORADOR(1, "ROLE_COLABORADOR"),ALUNO(2, "ROLE_ALUNO");

    private Integer codigo;
    private String descricao;

    Perfil(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer cod){
        if (cod == null){
            return null;
        }
        for (Perfil x: Perfil.values()){
            if (cod.equals(x.getCodigo())){
                return x;
            }
        }

        throw new IllegalArgumentException("Perfil inválido");
    }
}
