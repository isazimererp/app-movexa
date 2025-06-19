package com.movexa.api.repository;

import com.movexa.api.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Métodos personalizados podem ser adicionados aqui
    // Exemplo: List<Pedido> findByStatus(String status);
}