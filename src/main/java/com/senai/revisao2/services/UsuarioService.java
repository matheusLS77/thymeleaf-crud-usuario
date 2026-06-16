package com.senai.revisao2.services;

import com.senai.revisao2.dtos.UsuarioDto;
import com.senai.revisao2.entities.UsuarioEntity;
import com.senai.revisao2.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public void cadastrarUsuario(UsuarioDto dto) {
        repository.save(toEntity(dto));
    }

    public void atualizarUsuario(UsuarioDto dto) {
        Optional<UsuarioEntity> usuarioOP = repository.findById(dto.getId());

        if (usuarioOP.isPresent()) {
            UsuarioEntity entity = usuarioOP.get();

            entity.setNome(dto.getNome());
            entity.setEmail(dto.getEmail());

            if (!dto.getNome().isEmpty()) {
                entity.setSenha(dto.getSenha());
            }

            repository.save(entity);
        }
    }

    public void excluirUsuario(Long id) {
        repository.deleteById(id);

    }

    public List<UsuarioDto> listar() {
        List<UsuarioEntity> entities = repository.findAll();
        List<UsuarioDto> dtos = new ArrayList<>();

        for (UsuarioEntity entity : entities) {
            dtos.add(toDto(entity));
        }

        return dtos;
    }

    private UsuarioEntity toEntity(UsuarioDto dto) {
        UsuarioEntity entity = new UsuarioEntity();

        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setNome(dto.getNome());
        entity.setSenha(dto.getSenha());

        return entity;
    }

    private UsuarioDto toDto(UsuarioEntity entity) {
        UsuarioDto dto = new UsuarioDto();

        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setNome(entity.getNome());

        return dto;
    }

    public UsuarioDto verificarUsuario(Long id) {
        Optional<UsuarioEntity> dtoOp = repository.findById(id);
        UsuarioDto dto = new UsuarioDto();

        if (dtoOp.isPresent()) {
            dto = toDto(dtoOp.get());
        }

        return dto;
    }

}
