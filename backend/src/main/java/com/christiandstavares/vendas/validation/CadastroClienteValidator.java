package com.christiandstavares.vendas.validation;

import com.christiandstavares.vendas.controller.exception.CampoMensagem;
import com.christiandstavares.vendas.dto.NovoClienteDTO;
import com.christiandstavares.vendas.entity.Cliente;
import com.christiandstavares.vendas.enums.TipoCliente;
import com.christiandstavares.vendas.service.ClienteService;
import com.christiandstavares.vendas.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class CadastroClienteValidator implements ConstraintValidator<CadastroCliente, NovoClienteDTO> {

    @Autowired
    private ClienteService clienteService;

    @Override
    public void initialize(CadastroCliente ann) {
    }

    @Override
    public boolean isValid(NovoClienteDTO objDto, ConstraintValidatorContext context) {
        List<CampoMensagem> list = new ArrayList<>();
        Cliente cliente = clienteService.buscarPorEmail(objDto.getEmail());

        if (cliente != null) {
            list.add(new CampoMensagem("email", "E-mail já cadastrado"));
        }

        if (objDto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCodigo()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
            list.add(new CampoMensagem("cpfOuCnpj", "CPF inválido"));
        }

        if (objDto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCodigo()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
            list.add(new CampoMensagem("cpfOuCnpj", "CNPJ inválido"));
        }

        for (CampoMensagem e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMensagem()).addPropertyNode(e.getCampo()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}
