package com.senai.revisao2.controllers;

import com.senai.revisao2.dtos.ProdutoDto;
import com.senai.revisao2.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProdutoController {
    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping("/produtocadastrar")
    public String cadastrarProduto(@Valid @ModelAttribute("produto") ProdutoDto dto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "produtocadastrar";
        }

        service.cadastrar(dto);
        redirectAttributes.addFlashAttribute("mensagem", "Produto cadastrado com sucesso ");

        return "redirect:/produtolista";
    }

    @PostMapping("/produtoatualizar")
    public String atualizarProduto(@Valid @ModelAttribute("produto") ProdutoDto dto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "produtoatualizar";
        }

        service.atualizar(dto);
        redirectAttributes.addFlashAttribute("mensagem", "Usuário atualizado com sucesso ");

        return "redirect:/produtolista";
    }

    @DeleteMapping("/produtoexcluir/{id}")
    public ResponseEntity<String> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.ok().body("Excluído ");
    }

}
