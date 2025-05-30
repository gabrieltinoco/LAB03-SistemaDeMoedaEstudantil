package com.laboratorio03.campuscash.controllers;

import com.laboratorio03.campuscash.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
