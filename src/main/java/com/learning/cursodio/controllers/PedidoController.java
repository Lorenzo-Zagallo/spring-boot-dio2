package com.learning.cursodio.controllers;

import com.learning.cursodio.entities.Pedido;
import com.learning.cursodio.services.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {

    private PedidoService pedidoService;

    @PostMapping(value = "/save")
    public ResponseEntity<Pedido> salvaPedido(@RequestBody Pedido pedido) {
        return ResponseEntity.ok().body(pedidoService.save(pedido));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> buscaPedido(@PathVariable Long id) {
        return ResponseEntity.ok().body(pedidoService.findById(id));
    }

    @GetMapping(value = "/busca-todos")
    public ResponseEntity<List<Pedido>> buscaTodosPedido() {
        return ResponseEntity.ok().body(pedidoService.findAll());
    }
}
