package com.laboratorio03.campuscash.controllers;

import com.laboratorio03.campuscash.dto.AlunoDTO;
import com.laboratorio03.campuscash.dto.ProfessorDTO;
import com.laboratorio03.campuscash.services.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping("")
    public ModelAndView index () {
        return professorService.paginaProfessores();
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id){
        return professorService.mostrarProfessor(id);
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, ProfessorDTO professorDTO) {
        return professorService.editarProfessor(id, professorDTO);
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid ProfessorDTO professorDTO, BindingResult bindingResult) {
        return professorService.atualizarProfessor(id, professorDTO, bindingResult);
    }
}
