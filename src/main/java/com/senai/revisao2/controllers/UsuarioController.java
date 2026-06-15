package com.senai.revisao2.controllers;

import com.senai.revisao2.dtos.*;
import com.senai.revisao2.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public String realizarLogin(String email, String senha,
                                Model model, RedirectAttributes redirectAttributes){

        System.out.println("email=" + email + " senha=" + senha);

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setEmail(email);
        usuarioDto.setSenha(senha);

        UsuarioDto retorno = service.realizarLogin(usuarioDto);

        if (retorno.getNome() != null) {
            redirectAttributes.addFlashAttribute("usuario", " Bem-vindo ao sistema " + retorno.getNome() + "! ");

            return "redirect:/home";
        }

        model.addAttribute("erro","E-mail ou senha inválidos.");

        return "login";
    }

    @PostMapping("/usuarioinserir")
    public String inserirUsuario(@Valid @ModelAttribute("usuario") UsuarioDto dto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "usuarioinserir";
        }

        service.cadastrarUsuario(dto);
        redirectAttributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso ");

        return "redirect:/usuariolista";
    }

    @PostMapping("/usuarioatualizar")
    public String atualizarUsuario(@Valid @ModelAttribute("usuario") UsuarioDto dto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "usuarioatualizar";
        }

        service.atualizarUsuario(dto, dto.getId());
        redirectAttributes.addFlashAttribute("mensagem", "Usuário atualizado com sucesso ");

        return "redirect:/usuariolista";
    }

}
