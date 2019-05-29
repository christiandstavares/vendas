package com.christiandstavares.vendas.controller;

import com.christiandstavares.vendas.security.JWTUtils;
import com.christiandstavares.vendas.security.UserSS;
import com.christiandstavares.vendas.service.AuthService;
import com.christiandstavares.vendas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JWTUtils jwtUtils;

    @PostMapping("refresh-token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSS user = UsuarioService.usuarioLogado();
        String token = jwtUtils.gerarToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("forgot")
    public ResponseEntity<Void> forgot(@RequestBody String email) {
        authService.enviarNovaSenha(email);
        return ResponseEntity.noContent().build();
    }
}
