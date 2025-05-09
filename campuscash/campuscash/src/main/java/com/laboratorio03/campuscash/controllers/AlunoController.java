package com.laboratorio03.campuscash.controllers;

import com.laboratorio03.campuscash.dto.AlunoDTO;
import com.laboratorio03.campuscash.models.Aluno;
import com.laboratorio03.campuscash.repositories.AlunoRepository;
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

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("")
    public ModelAndView index () {

        List<Aluno> alunos = this.alunoRepository.findAll();
        ModelAndView mv = new ModelAndView("alunos/index");
        mv.addObject("alunos", alunos);
        mv.addObject("page", "alunos");
        return mv;
    }

    @GetMapping("/novo")
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView("alunos/novo");
        mv.addObject("requisicao", new AlunoDTO()); // <-- linha essencial
        mv.addObject("page", "alunos/novo");
        return mv;
    }


    @PostMapping("")
    public ModelAndView create(@Valid AlunoDTO requisicao, BindingResult bindingResult){

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

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id) {

        Optional<Aluno> optional = this.alunoRepository.findById(id);

        if (optional.isPresent()) {
            Aluno alunos = optional.get();
            ModelAndView mv = new ModelAndView("alunos/show");
            mv.addObject("page", "alunos/show");
            mv.addObject("alunos", alunos);
            return mv;
        } else {
            return this.retornaErroAlunos("Não foi possível encontrar o aluno #" + id +"!");
        }
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, AlunoDTO requisicao) {
        Optional<Aluno> optional = this.alunoRepository.findById(id);

        if (optional.isPresent()){
            Aluno aluno = optional.get();
            requisicao.fromAluno(aluno);
            ModelAndView mv = new ModelAndView("alunos/edit");
            mv.addObject("page","alunos/edit");
            mv.addObject("requisicao", requisicao);
            mv.addObject("alunoId", aluno.getId());
            return mv;
        } else {
            return this.retornaErroAlunos("Não foi possivel encontrar o aluno #" + id +"!");
        }
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid AlunoDTO requisicao, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("alunos/edit");
            mv.addObject("alunoId", id);
            mv.addObject("page", "alunos/edit");
            return mv;
        } else {
            Optional<Aluno> optional = this.alunoRepository.findById(id);
            if (optional.isPresent()) {
                Aluno aluno = requisicao.toAluno(optional.get());
                this.alunoRepository.save(aluno);
                ModelAndView mv = new ModelAndView("redirect:/alunos/" + aluno.getId());
                mv.addObject("page", "alunos/show");
                return mv;
            } else{
                return this.retornaErroAlunos("Erro de atualização: Não foi possível encontrar o aluno #" + id +"!");
            }
        }
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("redirect:/alunos");
        try {
            this.alunoRepository.deleteById(id);
            mv.addObject("mensagem", "Veiculo #" + id + " deletado com sucesso!");
            mv.addObject("erro", false);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            mv = this.retornaErroAlunos("Erro de delete: Não foi possível encontrar o veiculo #" + id +"!");
        }
        return mv;
    }


    private ModelAndView retornaErroAlunos(String mensagem) {
        ModelAndView mv = new ModelAndView("redirect:/alunos");
        mv.addObject("mensagem", mensagem);
        mv.addObject("erro", true);
        return mv;
    }

}
