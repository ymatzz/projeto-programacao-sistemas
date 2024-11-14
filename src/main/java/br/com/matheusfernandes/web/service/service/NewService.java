package br.com.matheusfernandes.web.service.service;

import br.com.matheusfernandes.web.service.dto.NewDTO;
import br.com.matheusfernandes.web.service.entity.Category;
import br.com.matheusfernandes.web.service.entity.New;
import br.com.matheusfernandes.web.service.entity.User;
import br.com.matheusfernandes.web.service.repository.CategoryRepository;
import br.com.matheusfernandes.web.service.repository.NewRepository;
import br.com.matheusfernandes.web.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NewService {

    private final NewRepository newRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public List<New> list() {
        try {
            return newRepository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar notícias", e);
        }
    }

    public New create(NewDTO newDTO) {
        try {
            New news = newMapperCreate(newDTO);
            newRepository.save(news);
            return news;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao criar notícia", e);
        }
    }

    public List<New> update(NewDTO newDTO) {
        try {
            if (!newRepository.existsById(newDTO.getId())) {
                throw new NoSuchElementException("Notícia não encontrada para atualização");
            }
            New news = newMapperUpdate(newDTO);
            newRepository.save(news);
            return list();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar notícia", e);
        }
    }

    public New patch(Long id, NewDTO newDTO) {
        try {
            New news = newRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Notícia não encontrada para atualização parcial"));

            if (newDTO.getName() != null) {
                news.setName(newDTO.getName());
            }
            if (newDTO.getCategoryId() != null) {
                Category category = categoryRepository.findById(newDTO.getCategoryId())
                        .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
                news.setCategory(category);
            }
            if (newDTO.getCreatedUserId() != null) {
                User user = userRepository.findById(newDTO.getCreatedUserId())
                        .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
                news.setCreatedUser(user);
            }
            if (newDTO.getContent() != null) {
                news.setContent(newDTO.getContent());
            }
            if (newDTO.getTags() != null) {
                news.setTags(newDTO.getTags());
            }

            news.setUpdatedAt(Instant.now());

            newRepository.save(news);
            return news;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erro ao atualizar parcialmente a notícia", e);
        }
    }

    public List<New> delete(Long id) {
        try {
            if (!newRepository.existsById(id)) {
                throw new NoSuchElementException("Notícia não encontrada para exclusão");
            }
            newRepository.deleteById(id);
            return list();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao excluir notícia", e);
        }
    }

    public New getNewById(long id) {
        try {
            return newRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Notícia não encontrada"));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar notícia", e);
        }
    }

    public List<New> getNewsByUserId(Long id) {
        try {
            return newRepository.getNewsByUserId(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar noticias do usuário",
                    e);
        }
    }

    public List<New> getNewsByCategoryId(Long id) {
        try {
            return newRepository.getNewsByCategoryId(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar noticias da categoria",
                    e);
        }
    }

    private New newMapperUpdate(NewDTO newDTO) {
        Category category = categoryRepository.findById(newDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
        User user = userRepository.findById(newDTO.getCreatedUserId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        New news = new New();
        news.setId(newDTO.getId());
        news.setName(newDTO.getName());
        news.setCategory(category);
        news.setCreatedUser(user);
        news.setContent(newDTO.getContent());
        news.setTags(newDTO.getTags());
        news.setUpdatedAt(Instant.now());
        return news;
    }

    private New newMapperCreate(NewDTO newDTO) {
        Category category = categoryRepository.findById(newDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
        User user = userRepository.findById(newDTO.getCreatedUserId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        New news = new New();
        news.setName(newDTO.getName());
        news.setCategory(category);
        news.setCreatedUser(user);
        news.setContent(newDTO.getContent());
        news.setTags(newDTO.getTags());
        news.setCreatedAt(Instant.now());
        return news;
    }
}
