package com.movexa.api.service;

import com.movexa.api.model.Pedido;
import com.movexa.api.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido criarPedido(Pedido pedido) {
        pedido.setStatus("pendente");
        return pedidoRepository.save(pedido);
    }


    public Pedido concluirPedido(Long id, Long finalizadoPor, String observacoes) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com id " + id));

        pedido.setStatus("concluido");
        pedido.setFinalizadoPor(finalizadoPor);
        pedido.setObservacoes(observacoes);

        return pedidoRepository.save(pedido);
    }
}
