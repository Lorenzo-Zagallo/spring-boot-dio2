package com.learning.cursodio.services;

import com.learning.cursodio.entities.Pedido;
import com.learning.cursodio.repositories.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private PedidoRepository pedidoRepository;

    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido findById(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }
}
