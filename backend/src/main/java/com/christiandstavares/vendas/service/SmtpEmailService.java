package com.christiandstavares.vendas.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

public class SmtpEmailService extends AbstractEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmtpEmailService.class);

    @Autowired
    private MailSender mailSender;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void enviarEmail(SimpleMailMessage simpleMailMessage) {
        LOGGER.info("Enviando e-mail...");
        mailSender.send(simpleMailMessage);
        LOGGER.info("E-mail enviado!");
    }

    @Override
    public void enviarEmailHtml(MimeMessage mimeMessage) {
        LOGGER.info("Enviando e-mail HTML...");
        javaMailSender.send(mimeMessage);
        LOGGER.info("E-mail enviado!");
    }
}
