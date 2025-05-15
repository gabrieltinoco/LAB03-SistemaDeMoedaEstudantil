package com.laboratorio03.campuscash.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Aluno extends Usuario{

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false, unique = true)
    private String rg;

    @Column(nullable = false)
    private String curso;

    public Aluno() {
        this.tipoUsuario = TipoUsuario.ALUNO; // Define o tipo automaticamente
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
