package com.laboratorio03.campuscash.services;

import com.laboratorio03.campuscash.models.Usuario;
import com.laboratorio03.campuscash.repositories.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ModelAndView formularioLogin() {
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("page", "login");
        return mv;
    }

    public ModelAndView login(@RequestParam String email, @RequestParam String senha, HttpSession session) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            if (usuario.getSenha().equals(senha)) {
                session.setAttribute("usuarioLogado", usuario);

                // Redirecionar conforme tipo
                switch (usuario.getTipoUsuario()) {
                    case ALUNO:
                        return new ModelAndView("redirect:/alunos/" + usuario.getId());
                    case EMPRESA:
                        return new ModelAndView("redirect:/empresa/dashboard");
                }
            }
        }

        ModelAndView mv = new ModelAndView("login");
        mv.addObject("erro", "Email ou senha inválidos.");
        return mv;
    }
}
