package com.christiandstavares.vendas.service;

import com.christiandstavares.vendas.entity.Pagamento;
import com.christiandstavares.vendas.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public List<Pagamento> salvarLista(List<Pagamento> pagamentos) {
        return pagamentoRepository.saveAll(pagamentos);
    }

    public Pagamento salvar(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }
}
