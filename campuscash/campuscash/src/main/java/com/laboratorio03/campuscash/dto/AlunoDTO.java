package com.laboratorio03.campuscash.dto;

import com.laboratorio03.campuscash.models.Aluno;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AlunoDTO {


    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String cpf;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String senha;

    @NotNull
    @NotBlank
    private String endereço;

    @NotNull
    @NotBlank
    private String rg;

    @NotNull
    @NotBlank
    private String curso;

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

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
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

    public Aluno toAluno(){
        Aluno aluno = new Aluno();
        return getAluno(aluno);
    }

    public Aluno toAluno(Aluno aluno){
        return getAluno(aluno);
    }

    private Aluno getAluno(Aluno aluno) {
        aluno.setNome(this.nome);
        aluno.setCpf(this.cpf);
        aluno.setEmail(this.email);
        aluno.setCurso(this.curso);
        aluno.setEndereço(this.endereço);
        aluno.setRg(this.rg);
        aluno.setSenha(this.senha);
        return aluno;
    }


    public void fromAluno(Aluno aluno){
        getAluno(aluno);
    }

    @Override
    public String toString() {
        return "RequisicaoAluno{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", endereço='" + endereço + '\'' +
                ", rg='" + rg + '\'' +
                ", curso='" + curso + '\'' +
                '}';
    }
}
