package com.senai.revisao2.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UsuarioDto {
    private Long id;

    @NotBlank(message = "Campo vazio")
    private String nome;

    @NotBlank(message = "Campo vazio")
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = "Campo vazio")
    private String senha;

    public UsuarioDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
