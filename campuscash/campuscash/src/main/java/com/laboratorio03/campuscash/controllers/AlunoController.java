package com.laboratorio03.campuscash.controllers;

import com.laboratorio03.campuscash.dto.AlunoDTO;
import com.laboratorio03.campuscash.models.Aluno;
import com.laboratorio03.campuscash.repositories.AlunoRepository;
import com.laboratorio03.campuscash.services.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    @GetMapping("")
    public ModelAndView index () {
        return alunoService.paginaAlunos();
    }

    @GetMapping("/novo")
    public ModelAndView novo() {
        return alunoService.novoAluno();
    }


    @PostMapping("")
    public ModelAndView create(@Valid AlunoDTO requisicao, BindingResult bindingResult){

        return alunoService.criarnovoAluno(requisicao, bindingResult);
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id) {

        return alunoService.mostrarAluno(id);
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, AlunoDTO requisicao) {
        return alunoService.editarAluno(id, requisicao);
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid AlunoDTO requisicao, BindingResult bindingResult) {
        return alunoService.atualizarAluno(id, requisicao, bindingResult);
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Long id) {
        return alunoService.deletarAluno(id);
    }

}
