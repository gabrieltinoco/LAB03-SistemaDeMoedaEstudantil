package com.laboratorio03.campuscash.models;


import jakarta.persistence.MappedSuperclass;


public abstract class  Pessoa {

    private String nome;
    private String cpf;
    private String email;
    private String senha;

}
