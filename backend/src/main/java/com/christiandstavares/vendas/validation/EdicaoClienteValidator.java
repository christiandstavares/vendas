package com.christiandstavares.vendas.validation;

import com.christiandstavares.vendas.controller.exception.CampoMensagem;
import com.christiandstavares.vendas.dto.ClienteDTO;
import com.christiandstavares.vendas.dto.NovoClienteDTO;
import com.christiandstavares.vendas.entity.Cliente;
import com.christiandstavares.vendas.enums.TipoCliente;
import com.christiandstavares.vendas.service.ClienteService;
import com.christiandstavares.vendas.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EdicaoClienteValidator implements ConstraintValidator<EdicaoCliente, ClienteDTO> {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public void initialize(EdicaoCliente ann) {
    }

    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
        List<CampoMensagem> list = new ArrayList<>();
        Cliente cliente = clienteService.buscarPorEmail(objDto.getEmail());
        Long idCliente = Long.parseLong(((Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE)).get("id"));

        if (cliente != null && !cliente.getId().equals(idCliente)) {
            list.add(new CampoMensagem("email", "E-mail já cadastrado"));
        }

        for (CampoMensagem e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMensagem()).addPropertyNode(e.getCampo()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}
