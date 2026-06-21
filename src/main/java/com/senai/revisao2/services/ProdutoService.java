package com.senai.revisao2.services;

import com.senai.revisao2.dtos.ProdutoDto;
import com.senai.revisao2.entities.ProdutoEntity;
import com.senai.revisao2.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(ProdutoDto produtoDto) {
        ProdutoEntity produtoEntity = toEntity(produtoDto);

        repository.save(produtoEntity);
    }

    public void atualizar(ProdutoDto produtoDto, long id) {
        Optional<ProdutoEntity> usuarioOP = repository.findById(produtoDto.getId());

        if (usuarioOP.isPresent()) {
            ProdutoEntity entity = usuarioOP.get();

            entity.setNome(produtoDto.getNome());
            entity.setDescricao(produtoDto.getDescricao());
            entity.setPreco(produtoDto.getPreco());
            entity.setEstoque(produtoDto.getEstoque());

            repository.save(entity);
        }
    }

    public List<ProdutoDto> listar() {
        List<ProdutoEntity> produtoEntityList = repository.findAll();
        List<ProdutoDto> produtoDtoList = new ArrayList<>();

        for (ProdutoEntity produtoEntity : produtoEntityList) {
            produtoDtoList.add(toDto(produtoEntity));
        }
        return produtoDtoList;
    }

    public void remover(long id) {
        repository.deleteById(id);
    }

    private ProdutoDto toDto(ProdutoEntity entity) {
        ProdutoDto dto = new ProdutoDto();

        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDescricao(entity.getDescricao());
        dto.setPreco(entity.getPreco());
        dto.setEstoque(entity.getEstoque());

        return dto;
    }

    private ProdutoEntity toEntity(ProdutoDto dto) {
        ProdutoEntity entity = new ProdutoEntity();

        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        entity.setEstoque(dto.getEstoque());

        return entity;
    }
}