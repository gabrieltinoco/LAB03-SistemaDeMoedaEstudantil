package com.laboratorio03.campuscash.controllers;

import com.laboratorio03.campuscash.dto.RequisicaoAluno;
import com.laboratorio03.campuscash.models.Aluno;
import com.laboratorio03.campuscash.repositories.AlunoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("")
    public ModelAndView index () {
        List<Aluno> alunos = alunoRepository.findAll();

        ModelAndView mv = new ModelAndView("aluno/index");
        mv.addObject("alunos", alunos);
        return mv;
    }

    @PostMapping("")
    public ModelAndView create(@Valid RequisicaoAluno requisicao, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("alunos/novo");
            mv.addObject("page", "alunos/novo");
            return mv;
        } else {
            Aluno aluno = requisicao.toAluno();
            this.alunoRepository.save(aluno);
            return new ModelAndView("redirect:/alunos");
        }
    }

}
