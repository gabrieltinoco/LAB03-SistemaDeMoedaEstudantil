package com.laboratorio03.campuscash.dto;

import com.laboratorio03.campuscash.models.Instituicao;
import com.laboratorio03.campuscash.models.Professor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProfessorDTO extends UsuarioDTO {

    @NotBlank(message = "O nome do professor é obrigatório.")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório.")
    private String cpf;

    @NotBlank(message = "O departamento é obrigatório")
    private String departamento;

    @NotNull(message = "A instituição é obrigatória.")
    private Long instituicaoId;

    private int saldoMoedas;

    private String nomeInstituicao;

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

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getSaldoMoedas() {
        return saldoMoedas;
    }

    public void setSaldoMoedas(int saldoMoedas) {
        this.saldoMoedas = saldoMoedas;
    }

    public Long getInstituicaoId() {
        return instituicaoId;
    }

    public void setInstituicaoId(Long instituicaoId) {
        this.instituicaoId = instituicaoId;
    }

    public String getNomeInstituicao() {
        return nomeInstituicao;
    }

    public void setNomeInstituicao(String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
    }

    private Professor toProfessor() {
        Professor professor = new Professor();
        return getProfessor(professor);
    }

    private Professor toProfessor(Professor professor) {
        return getProfessor(professor);
    }

    private Professor getProfessor(Professor professor){
        professor.setNome(this.nome);
        professor.setCpf(this.cpf);
        professor.setDepartamento(this.departamento);
        professor.setEmail(this.email);
        professor.setSenha(this.senha);
        professor.setSaldoMoedas(this.saldoMoedas);
        professor.setInstituicao(new Instituicao(this.instituicaoId));
        return professor;
    }

    private void fromProfessor(Professor professor) {
        this.nome = professor.getNome();
        this.cpf = professor.getCpf();
        this.departamento = professor.getDepartamento();
        this.email = professor.getEmail();
        this.senha = professor.getSenha();
        this.saldoMoedas = professor.getSaldoMoedas();
        this.instituicaoId = professor.getInstituicao() != null ? professor.getInstituicao().getId() : null;
        this.nomeInstituicao = professor.getInstituicao() != null ? professor.getInstituicao().getNome() : null;
    }
}
