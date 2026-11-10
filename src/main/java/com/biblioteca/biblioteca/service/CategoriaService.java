package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.model.Categoria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {

    private final List<Categoria> categorias = new ArrayList<>();
    private long proximoId = 1;

    public List<Categoria> listar() {
        return categorias;
    }

    public Categoria buscarPorId(Long id) {
        for (Categoria categoria : categorias) {
            if (categoria.getId().equals(id)) {
                return categoria;
            }
        }

        return null;
    }

    public void cadastrar(Categoria categoria) {
        categoria.setId(proximoId++);
        categorias.add(categoria);
    }

    public void atualizar(Long id, Categoria categoriaAtualizada) {
        Categoria categoria = buscarPorId(id);

        if (categoria != null) {
            categoria.setNome(categoriaAtualizada.getNome());
        }
    }

    public void excluir(Long id) {
        categorias.removeIf(categoria -> categoria.getId().equals(id));
    }
}