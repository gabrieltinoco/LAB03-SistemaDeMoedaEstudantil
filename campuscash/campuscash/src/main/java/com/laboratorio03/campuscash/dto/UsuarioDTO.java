package com.laboratorio03.campuscash.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UsuarioDTO {


    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O email deve ser válido.")
    protected String email;

    @NotBlank(message = "A senha é obrigatória.")
    @NotBlank
    protected String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
