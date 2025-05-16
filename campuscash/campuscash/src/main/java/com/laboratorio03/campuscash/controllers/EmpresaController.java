package com.laboratorio03.campuscash.controllers;


import com.laboratorio03.campuscash.dto.EmpresaDTO;
import com.laboratorio03.campuscash.services.EmpresaService;
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
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    EmpresaService empresaService;

    @GetMapping("")
    public ModelAndView index () {
        return empresaService.paginaEmpresas();
    }

    @GetMapping("/novo")
    public ModelAndView novo() {
        return empresaService.novaEmpresa();
    }

    @PostMapping("")
    public ModelAndView create(@Valid EmpresaDTO requisicao, BindingResult bindingResult){
        return empresaService.criarnovaEmpresa(requisicao, bindingResult);
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id) {
        return empresaService.mostrarEmpresa(id);
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, EmpresaDTO requisicao) {
        return empresaService.editarEmpresa(id, requisicao);
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid EmpresaDTO requisicao, BindingResult bindingResult) {
        return empresaService.atualizarEmpresa(id, requisicao, bindingResult);
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Long id) {
        return empresaService.deletarEmpresa(id);
    }


}
