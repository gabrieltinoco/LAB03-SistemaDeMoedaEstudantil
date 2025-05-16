package com.laboratorio03.campuscash.dto;

import com.laboratorio03.campuscash.models.Empresa;
import jakarta.validation.constraints.NotBlank;

public class EmpresaDTO extends UsuarioDTO {

    @NotBlank(message = "O nome da empresa é obrigatório")
    private String nome;

    @NotBlank(message = "O CNPJ da empresa é obrigatório")
    private String cnpj;



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

    public Empresa toEmpresa(){
        Empresa empresa = new Empresa();
        return getEmpresa(empresa);
    }

    private Empresa getEmpresa(Empresa empresa) {
        empresa.setNome(this.nome);
        empresa.setCnpj(this.cnpj);
        empresa.setEmail(this.email);
        empresa.setSenha(this.senha);
        return empresa;
    }

    public Empresa toEmpresa(Empresa empresa){
        return getEmpresa(empresa);
    }



}
