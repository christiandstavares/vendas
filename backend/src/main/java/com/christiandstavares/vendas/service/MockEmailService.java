package com.christiandstavares.vendas.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public class MockEmailService extends AbstractEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void enviarEmail(SimpleMailMessage simpleMailMessage) {
        LOGGER.info("Simulando envio de e-mail...");
        LOGGER.info(simpleMailMessage.toString());
        LOGGER.info("E-mail enviado!");
    }

    @Override
    public void enviarEmailHtml(MimeMessage mimeMessage) {
        LOGGER.info("Simulando envio de e-mail HTML...");
        LOGGER.info(mimeMessage.toString());
        LOGGER.info("E-mail enviado!");
    }
}
