package com.laboratorio03.campuscash.services;

import com.laboratorio03.campuscash.dto.AlunoDTO;
import com.laboratorio03.campuscash.dto.ProfessorDTO;
import com.laboratorio03.campuscash.models.Aluno;
import com.laboratorio03.campuscash.models.Professor;
import com.laboratorio03.campuscash.repositories.InstituicaoRepository;
import com.laboratorio03.campuscash.repositories.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private InstituicaoRepository instituicaoRepository;

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

    public ModelAndView editarProfessor(@PathVariable Long id, ProfessorDTO professorDTO){
        Optional<Professor> optional = this.professorRepository.findById(id);

        if (optional.isPresent()){
            Professor professor = optional.get();
            professorDTO.fromProfessor(professor);
            ModelAndView mv = new ModelAndView("professores/edit");
            mv.addObject("page","professores/edit");
            mv.addObject("requisicao", professorDTO);
            mv.addObject("professorId", professor.getId());
            mv.addObject("instituicoes", instituicaoRepository.findAll());
            return mv;
        } else {
            return this.retornaErroProfessor("Não foi possivel encontrar o professor #" + id +"!");
        }
    }

    public ModelAndView atualizarProfessor(@PathVariable Long id, @Valid ProfessorDTO professorDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("professores/edit");
            mv.addObject("professorId", id);
            mv.addObject("page", "professores/edit");
            mv.addObject("requisicao", professorDTO); // necessário para mostrar os erros no formulário
            mv.addObject("instituicoes", instituicaoRepository.findAll());
            return mv;
        } else {
            Optional<Professor> optional = this.professorRepository.findById(id);
            if (optional.isPresent()) {
                Professor professor = professorDTO.toProfessor(optional.get());
                this.professorRepository.save(professor);
                ModelAndView mv = new ModelAndView("redirect:/professores/" + professor.getId());
                mv.addObject("page", "professores/show");
                return mv;
            } else{
                return this.retornaErroProfessor("Erro de atualização: Não foi possível encontrar o professor #" + id +"!");
            }
        }
    }

    private ModelAndView retornaErroProfessor(String mensagem){
        ModelAndView mv = new ModelAndView("redirect:/professores");
        mv.addObject("mensagem", mensagem);
        mv.addObject("erro", true);
        return mv;
    }
}
