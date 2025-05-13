package com.laboratorio03.campuscash.dto;

import com.laboratorio03.campuscash.models.Aluno;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AlunoDTO {


    @NotBlank(message = "O nome do aluno é obrigatório.")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório.")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve estar no formato 000.000.000-00")
    private String cpf;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O email deve ser válido.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;

    @NotBlank(message = "O endereço é obrigatório.")
    private String endereco;

    @NotBlank(message = "O RG é obrigatório.")
    private String rg;

    @NotBlank(message = "O curso é obrigatório.")
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
        aluno.setEndereco(this.endereco);
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
                ", endereço='" + endereco + '\'' +
                ", rg='" + rg + '\'' +
                ", curso='" + curso + '\'' +
                '}';
    }
}
