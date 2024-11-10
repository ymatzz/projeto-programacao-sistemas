package br.com.matheusfernandes.web.service.service;

import br.com.matheusfernandes.web.service.entity.Category;
import br.com.matheusfernandes.web.service.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> list() {
        try {
            Sort sort = Sort.by("name").ascending();
            return categoryRepository.findAll(sort);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar categorias", e);
        }
    }

    public Category create(Category category) {
        try {
            categoryRepository.save(category);
            return category;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao criar categoria", e);
        }
    }

    public List<Category> update(Category category) {
        try {
            if (!categoryRepository.existsById(category.getId())) {
                throw new NoSuchElementException("Categoria não encontrada para atualização");
            }
            categoryRepository.save(category);
            return list();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar categoria", e);
        }
    }

    public List<Category> delete(Long id) {
        try {
            if (!categoryRepository.existsById(id)) {
                throw new NoSuchElementException("Categoria não encontrada para exclusão");
            }
            categoryRepository.deleteById(id);
            return list();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao excluir categoria", e);
        }
    }

    public Category getCategoryById(Long id) {
        try {
            return categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Categoria não encontrada"));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar categoria", e);
        }
    }
}
