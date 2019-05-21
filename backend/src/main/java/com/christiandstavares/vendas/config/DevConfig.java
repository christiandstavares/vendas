package com.christiandstavares.vendas.config;

import com.christiandstavares.vendas.service.DBService;
import com.christiandstavares.vendas.service.EmailService;
import com.christiandstavares.vendas.service.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String hibernateDdlAuto;

    @Bean
    public boolean instantiateDatabase() throws ParseException {

        if (!hibernateDdlAuto.equals("create")) {
            return false;
        }

        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }
}
