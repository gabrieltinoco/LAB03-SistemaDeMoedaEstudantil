package com.laboratorio03.campuscash.controllers;

import com.laboratorio03.campuscash.models.Usuario;
import com.laboratorio03.campuscash.repositories.UsuarioRepository;
import com.laboratorio03.campuscash.services.AlunoService;
import com.laboratorio03.campuscash.services.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public ModelAndView formularioLogin() {
        return loginService.formularioLogin();
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam String email, @RequestParam String senha, HttpSession session) {

        return loginService.login(email, senha, session);
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:/login");
    }
}
