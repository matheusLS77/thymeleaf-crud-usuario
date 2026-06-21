package com.senai.revisao2.controllers;

import com.senai.revisao2.dtos.ProdutoDto;
import com.senai.revisao2.services.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProdutoPageController {
    private final ProdutoService service;

    public ProdutoPageController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping("/produtocadastrar")
    public String getCadastrar(Model model) {
        model.addAttribute("produto", new ProdutoDto());
        return "produtocadastrar";
    }

    @GetMapping("/produtolista")
    public String getProdutos(Model model) {
        List<ProdutoDto> produtos = service.listar();

        model.addAttribute("produtos", produtos);
        return "produtolista";
    }

    @GetMapping("/produtoatualizar/{id}")
    public String getAtualizar(Model model, @PathVariable Long id) {
        ProdutoDto dto = service.obterProduto(id);

        model.addAttribute("produto", dto);
        return "produtoatualizar";
    }
}
