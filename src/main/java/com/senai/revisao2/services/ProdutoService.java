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

    public void atualizar(ProdutoDto produtoDto) {
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
        List<ProdutoEntity> produtos = repository.findAll();
        List<ProdutoDto> dtos = new ArrayList<>();

        for (ProdutoEntity produtoEntity : produtos) {
            dtos.add(toDto(produtoEntity));
        }
        return dtos;
    }

    public void remover(long id) {
        repository.deleteById(id);
    }

    public ProdutoDto obterProduto(Long id) {
        Optional<ProdutoEntity> dtoOp = repository.findById(id);
        ProdutoDto dto = new ProdutoDto();

        if (dtoOp.isPresent()) {
            dto = toDto(dtoOp.get());
        }

        return dto;
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