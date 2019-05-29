package com.christiandstavares.vendas.service;

import com.christiandstavares.vendas.entity.Cliente;
import com.christiandstavares.vendas.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public void enviarNovaSenha(String email) {

        Cliente cliente = clienteService.buscarPorEmail(email);
        if (cliente == null) {
            throw new ObjectNotFoundException("Email não encontrado");
        }

        String novaSenha = novaSenha();
        cliente.setSenha(passwordEncoder.encode(novaSenha));

        clienteService.salvar(cliente);
        emailService.enviarEmailDeNovaSenha(cliente, novaSenha);
    }

    private String novaSenha() {
        char[] vet = new char[10];
        Random random = new Random();

        for (int i=0; i<10; i++) {
            int opt = random.nextInt(3);

            if (opt == 0) { // gera um digito
                vet[i] = (char) (random.nextInt(10) + 48);
            } else if (opt == 1) { // gera letra maiuscula
                vet[i] = (char) (random.nextInt(26) + 65);
            } else { // gera letra minuscula
                vet[i] = (char) (random.nextInt(26) + 97);
            }
        }

        return new String(vet);
    }
}
