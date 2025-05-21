package com.laboratorio03.campuscash.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Empresa extends Usuario{

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cnpj;

    public Empresa() {
        this.tipoUsuario = TipoUsuario.EMPRESA;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
