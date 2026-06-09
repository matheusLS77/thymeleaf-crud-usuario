package com.senai.revisao2.services;

import com.senai.revisao2.dtos.UsuarioDto;
import com.senai.revisao2.entities.UsuarioEntity;
import com.senai.revisao2.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public UsuarioDto realizarLogin(UsuarioDto usuarioDto){
       Optional<UsuarioEntity> usuarioOP = repository.findByEmailAndSenha(usuarioDto.getEmail(), usuarioDto.getSenha());

       UsuarioDto dto = new UsuarioDto();

       if (usuarioOP.isPresent()) {
           dto = toDto(usuarioOP.get());
       }

       return dto;
    }

    private UsuarioEntity toEntity(UsuarioDto dto) {
        UsuarioEntity entity = new UsuarioEntity();

        entity.setEmail(dto.getEmail());
        entity.setNome(dto.getNome());
        entity.setSenha(dto.getSenha());

        return entity;
    }

    private UsuarioDto toDto(UsuarioEntity entity) {
        UsuarioDto dto = new UsuarioDto();

        dto.setEmail(entity.getEmail());
        dto.setNome(entity.getNome());

        return dto;
    }

}
