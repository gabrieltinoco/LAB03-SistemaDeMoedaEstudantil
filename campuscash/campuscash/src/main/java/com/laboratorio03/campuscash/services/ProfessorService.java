package com.laboratorio03.campuscash.services;

import com.laboratorio03.campuscash.models.Professor;
import com.laboratorio03.campuscash.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public ModelAndView paginaProfessores(){
        List<Professor> professores = professorRepository.findAll();
        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);
        mv.addObject("page", "professores)");
        return mv;
    }

    public ModelAndView mostrarProfessor(@PathVariable Long id){

        Optional<Professor> optional = this.professorRepository.findById(id);

        if (optional.isPresent()) {
            Professor professores = optional.get();
            ModelAndView mv = new ModelAndView("professores/show");
            mv.addObject("page", "professores/show");
            mv.addObject("professores", professores);
            return mv;
        } else {
            return this.retornaErroProfessor("Não foi possível encontrar o professor #" + id +"!");
        }
    }


    private ModelAndView retornaErroProfessor(String mensagem){
        ModelAndView mv = new ModelAndView("redirect:/professores");
        mv.addObject("mensagem", mensagem);
        mv.addObject("erro", true);
        return mv;
    }
}
