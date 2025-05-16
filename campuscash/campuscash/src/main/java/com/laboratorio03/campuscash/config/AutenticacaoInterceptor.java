package com.laboratorio03.campuscash.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class AutenticacaoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object usuario = request.getSession().getAttribute("usuarioLogado");
        if (usuario == null) {
            // Redireciona para login com parâmetro de aviso
            response.sendRedirect("/login?mensagem=Favor+fazer+login+para+continuar&erro=true");
            return false;
        }
        return true;
    }
}