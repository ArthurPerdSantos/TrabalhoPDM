package com.example.trabalhopdm.models;


public class PressaoModel {
    public String id_usuario;
    public Integer pressao_sistolica;
    public Integer pressao_diastolica;
    public String criacao;



    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getPressao_sistolica() {
        return pressao_sistolica;
    }

    public void setPressao_sistolica(Integer pressao_sistolica) {
        this.pressao_sistolica = pressao_sistolica;
    }

    public Integer getPressao_diastolica() {
        return pressao_diastolica;
    }

    public void setPressao_diastolica(Integer pressao_diastolica) {
        this.pressao_diastolica = pressao_diastolica;
    }

    public String getCriacao() {
        return criacao;
    }

    public void setCriacao(String criacao) {
        this.criacao = criacao;
    }
}
