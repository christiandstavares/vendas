package com.christiandstavares.vendas.service;

import com.christiandstavares.vendas.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class UsuarioService {

    public static UserSS usuarioLogado() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
