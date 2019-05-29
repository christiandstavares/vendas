package com.christiandstavares.vendas.service;

import com.christiandstavares.vendas.entity.Cliente;
import com.christiandstavares.vendas.entity.Pedido;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void enviarEmailDeConfirmacaoDePedido(Pedido pedido);

    void enviarEmail(SimpleMailMessage simpleMailMessage);

    void enviarEmailHtmlDeConfirmacaoDePedido(Pedido pedido);

    void enviarEmailHtml(MimeMessage mimeMessage);

    void enviarEmailDeNovaSenha(Cliente cliente, String novaSenha);
}
