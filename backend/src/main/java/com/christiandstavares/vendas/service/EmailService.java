package com.christiandstavares.vendas.service;

import com.christiandstavares.vendas.entity.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void enviarEmailDeConfirmacaoDePedido(Pedido pedido);

    void enviarEmail(SimpleMailMessage simpleMailMessage);
}
