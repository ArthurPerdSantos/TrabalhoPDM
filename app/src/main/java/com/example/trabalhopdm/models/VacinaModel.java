package com.example.trabalhopdm.models;

import java.util.Date;

public class VacinaModel {
    public String id_usario;
    public String nome_vacina;
    public String data_aplicacao;
    public String nome_laboratorio;
    public String dose;

    public VacinaModel() {
    }

    public String getId_usario() {
        return id_usario;
    }

    public void setId_usario(String id_usario) {
        this.id_usario = id_usario;
    }

    public String getNome_vacina() {
        return nome_vacina;
    }

    public void setNome_vacina(String nome_vacina) {
        this.nome_vacina = nome_vacina;
    }

    public String getData_aplicacao() {
        return data_aplicacao;
    }

    public void setData_aplicacao(String data_aplicacao) {
        this.data_aplicacao = data_aplicacao;
    }

    public String getNome_laboratorio() {
        return nome_laboratorio;
    }

    public void setNome_laboratorio(String nome_laboratorio) {
        this.nome_laboratorio = nome_laboratorio;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }
}
