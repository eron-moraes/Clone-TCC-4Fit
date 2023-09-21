package com.fit.domain.enums;

public enum Turno {

    MANHA(0, "MANHÃ"), TARDE(1, "TARDE"), NOITE(2, "NOITE");

    private Integer codigo;
    private String descricao;

    Turno(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Turno toEnum(Integer cod){
        if (cod == null){
            return null;
        }
        for (Turno x: Turno.values()){
            if (cod.equals(x.getCodigo())){
                return x;
            }
        }

        throw new IllegalArgumentException("Turno inválido!");
    }
}
