package com.example.trabalhopdm.models;

import java.util.Date;

public class BatimentosModel {
    public String id_usuario;
    public Integer batimentos_por_segundo;
    public Integer numero_de_batimentos_para_alerta;
    public String criacao;

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getBatimentos_por_segundo() {
        return batimentos_por_segundo;
    }

    public void setBatimentos_por_segundo(Integer batimentos_por_segundo) {
        this.batimentos_por_segundo = batimentos_por_segundo;
    }

    public Integer getNumero_de_batimentos_para_alerta() {
        return numero_de_batimentos_para_alerta;
    }

    public void setNumero_de_batimentos_para_alerta(Integer numero_de_batimentos_para_alerta) {
        this.numero_de_batimentos_para_alerta = numero_de_batimentos_para_alerta;
    }

    public String getCriacao() {
        return criacao;
    }

    public void setCriacao(String criacao) {
        this.criacao = criacao;
    }

}
