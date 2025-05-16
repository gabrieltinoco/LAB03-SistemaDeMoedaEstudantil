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

    public ModelAndView login(String email, String senha, HttpSession session) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();

            if (usuario.getSenha().equals(senha)) {
                session.setAttribute("usuarioLogado", usuario);

                String redirectUrl = switch (usuario.getTipoUsuario()) {
                    case ALUNO -> "/alunos/" + usuario.getId();
                    case EMPRESA -> "/empresa/dashboard";
                    default -> "/";
                };

                // Redireciona com mensagem de sucesso
                return new ModelAndView("redirect:" + redirectUrl + "?mensagem=Login+realizado+com+sucesso&erro=false");
            }
        }

        // Redireciona com mensagem de erro
        return new ModelAndView("redirect:/login?mensagem=Email+ou+senha+inválidos&erro=true");
    }
}
