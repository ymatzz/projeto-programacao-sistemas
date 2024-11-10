package br.com.matheusfernandes.web.service.service;

import br.com.matheusfernandes.web.service.dto.CommentDTO;
import br.com.matheusfernandes.web.service.entity.Comment;
import br.com.matheusfernandes.web.service.entity.New;
import br.com.matheusfernandes.web.service.entity.User;
import br.com.matheusfernandes.web.service.repository.CommentRepository;
import br.com.matheusfernandes.web.service.repository.NewRepository;
import br.com.matheusfernandes.web.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final NewRepository newRepository;

    public List<Comment> list() {
        try {
            return commentRepository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar comentários", e);
        }
    }

    public Comment create(CommentDTO commentDTO) {
        try {
            Comment comment = commentMapperCreate(commentDTO);
            commentRepository.save(comment);
            return comment;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao criar comentário", e);
        }
    }

    public List<Comment> update(CommentDTO commentDTO) {
        try {
            if (!commentRepository.existsById(commentDTO.getId())) {
                throw new NoSuchElementException("Comentário não encontrado para atualização");
            }
            Comment comment = commentMapperUpdate(commentDTO);
            commentRepository.save(comment);
            return list();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar comentário", e);
        }
    }

    public List<Comment> delete(Long id) {
        try {
            if (!commentRepository.existsById(id)) {
                throw new NoSuchElementException("Comentário não encontrado para exclusão");
            }
            commentRepository.deleteById(id);
            return list();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao excluir comentário", e);
        }
    }

    public Comment getCommentById(long id) {
        try {
            return commentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Comentário não encontrado"));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar comentário", e);
        }
    }

    public List<Comment> getCommentsByUserId(Long id) {
        try {
            return commentRepository.getCommentByUserId(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar comentários do usuário", e);
        }
    }

    public List<Comment> getCommentsByNewId(Long id) {
        try {
            return commentRepository.getCommentByNewId(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar comentários da notícia", e);
        }
    }

    private Comment commentMapperUpdate(CommentDTO commentDTO) {
        User user = userRepository.findById(commentDTO.getCreatedUserId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        New news = newRepository.findById(commentDTO.getCreatedNewId())
                .orElseThrow(() -> new IllegalArgumentException("Notícia não encontrada"));
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setCreatedUser(user);
        comment.setCreatedNew(news);
        comment.setMessage(commentDTO.getMessage());
        return comment;
    }

    private Comment commentMapperCreate(CommentDTO commentDTO) {
        User user = userRepository.findById(commentDTO.getCreatedUserId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        New news = newRepository.findById(commentDTO.getCreatedNewId())
                .orElseThrow(() -> new IllegalArgumentException("Notícia não encontrada"));
        Comment comment = new Comment();
        comment.setCreatedUser(user);
        comment.setCreatedNew(news);
        comment.setMessage(commentDTO.getMessage());
        return comment;
    }
}
