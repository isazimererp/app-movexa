package com.movexa.api.controller;

import com.movexa.api.model.Pedido;
import com.movexa.api.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/coletas")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<?> listarPedidos() {
        return ResponseEntity.ok().body(pedidoService.listarPedidos());
    }

    @PostMapping
    public ResponseEntity<?> criarColeta(@RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoService.criarPedido(pedido);
        return ResponseEntity.ok().body(novoPedido);
    }

    @PostMapping("/{id}/concluir")
    public ResponseEntity<?> concluirColeta(
            @PathVariable Long id,
            @RequestBody ConcluirPedidoRequest request) {

        Pedido pedidoAtualizado = pedidoService.concluirPedido(
                id,
                request.getFinalizadoPor(),
                request.getObservacoes()
        );

        return ResponseEntity.ok().body(pedidoAtualizado);
    }

    // Classe auxiliar para receber o payload de conclusão
    public static class ConcluirPedidoRequest {
        private Long finalizadoPor;
        private String observacoes;

        // Getters e Setters
        public Long getFinalizadoPor() {
            return finalizadoPor;
        }

        public void setFinalizadoPor(Long finalizadoPor) {
            this.finalizadoPor = finalizadoPor;
        }

        public String getObservacoes() {
            return observacoes;
        }

        public void setObservacoes(String observacoes) {
            this.observacoes = observacoes;
        }
    }
}
