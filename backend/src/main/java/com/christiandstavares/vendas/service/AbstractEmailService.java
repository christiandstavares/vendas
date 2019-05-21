package com.christiandstavares.vendas.service;

import com.christiandstavares.vendas.entity.Pedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void enviarEmailDeConfirmacaoDePedido(Pedido pedido) {
        SimpleMailMessage mailMessage = prepareSimpleMailMessageFromPedido(pedido);
        enviarEmail(mailMessage);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(pedido.getCliente().getEmail());
        mailMessage.setFrom(sender);
        mailMessage.setSubject("Pedido confirmado! Código: " + pedido.getId());
        mailMessage.setSentDate(new Date(System.currentTimeMillis()));
        mailMessage.setText(pedido.toString());

        return mailMessage;
    }
}
