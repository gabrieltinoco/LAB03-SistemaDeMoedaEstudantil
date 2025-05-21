package com.laboratorio03.campuscash.dto;

import com.laboratorio03.campuscash.models.Empresa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class EmpresaDTO extends UsuarioDTO {

    @NotBlank(message = "O nome da empresa é obrigatório")
    private String nome;

    @NotBlank(message = "O CNPJ da empresa é obrigatório")
    @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}", message = "O CNPJ deve estar no formato 00.000.000/0000-00")
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
    public Empresa toEmpresa(Empresa empresa){
        return getEmpresa(empresa);
    }

    private Empresa getEmpresa(Empresa empresa) {
        empresa.setNome(this.nome);
        empresa.setCnpj(this.cnpj);
        empresa.setEmail(this.email);
        empresa.setSenha(this.senha);
        return empresa;
    }

    public void fromEmpresa(Empresa empresa){
        this.nome = empresa.getNome();
        this.cnpj = empresa.getCnpj();
        this.email = empresa.getEmail();
        this.senha = empresa.getSenha();
    }

    @Override
    public String toString() {
        return "EmpresaDTO{" +
                "nome='" + nome + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
