package com.learning.cursodio.controllers;

import com.learning.cursodio.entities.Produto;
import com.learning.cursodio.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping(value = "/save")
    public ResponseEntity<Produto> salvaProduto(@RequestBody Produto produto) {
        return ResponseEntity.ok().body(produtoService.save(produto));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> buscaProduto(@PathVariable Long id) {
        return ResponseEntity.ok().body(produtoService.findById(id));
    }

    @GetMapping(value = "/busca-todos")
    public ResponseEntity<List<Produto>> buscaTodosProdutos() {
        return ResponseEntity.ok().body(produtoService.findAll());
    }
}
