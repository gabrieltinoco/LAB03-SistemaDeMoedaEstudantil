package com.laboratorio03.campuscash.services;

import com.laboratorio03.campuscash.dto.EmpresaDTO;
import com.laboratorio03.campuscash.models.Empresa;
import com.laboratorio03.campuscash.repositories.EmpresaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public ModelAndView paginaEmpresas() {

        List<Empresa> empresas = this.empresaRepository.findAll();
        ModelAndView mv = new ModelAndView("empresas/index");
        mv.addObject("empresas", empresas);
        mv.addObject("page", "empresas");
        return mv;
    }

    public ModelAndView novaEmpresa() {
        ModelAndView mv = new ModelAndView("empresas/novo");
        mv.addObject("requisicao", new EmpresaDTO());
        mv.addObject("page", "empresas/novo");
        return mv;
    }

    public ModelAndView criarnovaEmpresa(@Valid EmpresaDTO requisicao, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("empresas/novo");
            mv.addObject("page", "empresas/novo");
            mv.addObject("requisicao", requisicao);
            return mv;
        } else {
            Empresa empresa = requisicao.toEmpresa();
            this.empresaRepository.save(empresa);
            return new ModelAndView("redirect:/empresas");
        }
    }

    public ModelAndView mostrarEmpresa(@PathVariable Long id){
        Optional<Empresa> optional = this.empresaRepository.findById(id);

        if (optional.isPresent()) {
            Empresa empresa = optional.get();
            ModelAndView mv = new ModelAndView("empresas/show");
            mv.addObject("page", "empresas/show");
            mv.addObject("empresas", empresa);
            return mv;
        } else {
            return this.retornaErroEmpresa("Não foi possível encontrar a empresa #" + id +"!");
        }
    }

    public ModelAndView editarEmpresa(@PathVariable Long id, EmpresaDTO requisicao){
        Optional<Empresa> optional = this.empresaRepository.findById(id);

        if (optional.isPresent()){
            Empresa empresa = optional.get();
            requisicao.fromEmpresa(empresa);
            ModelAndView mv = new ModelAndView("empresas/edit");
            mv.addObject("page","empresas/edit");
            mv.addObject("requisicao", requisicao);
            mv.addObject("empresaId", empresa.getId());
            return mv;
        } else {
            return this.retornaErroEmpresa("Não foi possivel encontrar a empresa #" + id +"!");
        }
    }

    public ModelAndView atualizarEmpresa(@PathVariable Long id, @Valid EmpresaDTO requisicao, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("empresas/edit");
            mv.addObject("empresaId", id);
            mv.addObject("page", "empresas/edit");
            mv.addObject("requisicao", requisicao); // necessário para mostrar os erros no formulário
            return mv;
        } else {
            Optional<Empresa> optional = this.empresaRepository.findById(id);
            if (optional.isPresent()) {
                Empresa empresa = requisicao.toEmpresa(optional.get());
                this.empresaRepository.save(empresa);
                ModelAndView mv = new ModelAndView("redirect:/empresas/" + empresa.getId());
                mv.addObject("page", "empresas/show");
                return mv;
            } else{
                return this.retornaErroEmpresa("Erro de atualização: Não foi possível encontrar a empresa #" + id +"!");
            }
        }
    }

    public ModelAndView deletarEmpresa(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("redirect:/empresas");
        try {
            this.empresaRepository.deleteById(id);
            mv.addObject("mensagem", "Empresa #" + id + " deletado com sucesso!");
            mv.addObject("erro", false);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            mv = this.retornaErroEmpresa("Erro de delete: Não foi possível encontrar a empresa #" + id +"!");
        }
        return mv;
    }

    private ModelAndView retornaErroEmpresa(String mensagem) {
        ModelAndView mv = new ModelAndView("redirect:/empresas");
        mv.addObject("mensagem", mensagem);
        mv.addObject("erro", true);
        return mv;
    }
}
