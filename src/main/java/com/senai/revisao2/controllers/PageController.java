package com.senai.revisao2.controllers;

import com.senai.revisao2.entities.UsuarioEntity;
import com.senai.revisao2.repositories.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageController {

    private final UsuarioRepository repository;

    public PageController(UsuarioRepository repository) {
        this.repository = repository;
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
        List<UsuarioEntity> usuarios = repository.findAll();

        model.addAttribute("usuarios", usuarios);
        return "usuariolista";
    }
}
