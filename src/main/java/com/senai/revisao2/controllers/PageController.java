package com.senai.revisao2.controllers;

import com.senai.revisao2.dtos.UsuarioDto;
import com.senai.revisao2.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageController {

    private final UsuarioService service;

    public PageController(UsuarioService repository) {
        this.service = repository;
    }


    @GetMapping("/")
    public String getIndex(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @GetMapping("/home")
    public String getHome(){
        return "home";
    }

    @GetMapping("/usuariolista")
    public String getUsuarios(Model model){
        List<UsuarioDto> usuarios = service.listar();

        model.addAttribute("usuarios", usuarios);
        return "usuariolista";
    }

    @GetMapping("/usuarioinserir")
    public String getCadastrar(Model model) {
        model.addAttribute("usuario", new UsuarioDto());
        return "usuarioinserir";
    }

    @GetMapping("/usuarioatualizar")
    public String getAtualizar(Model model) {
        model.addAttribute("usuario", new UsuarioDto());
        return "usuarioatualizar";
    }
}
