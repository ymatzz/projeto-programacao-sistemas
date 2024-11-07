package br.com.matheusfernandes.web.service.service;

import br.com.matheusfernandes.web.service.entity.Comment;
import br.com.matheusfernandes.web.service.entity.New;
import br.com.matheusfernandes.web.service.entity.User;
import br.com.matheusfernandes.web.service.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public List<Comment> list(){
        return commentRepository.findAll();
    }

    public List<Comment> create(Comment comment){
        commentRepository.save(comment);
        return list();
    }

    public List<Comment> update(Comment comment){
        commentRepository.save(comment);
        return list();
    }

    public List<Comment> delete(Long id){
        commentRepository.deleteById(id);
        return list();
    }

    public Comment getCommentById(long id){
        return commentRepository.getReferenceById(id);
    }
   
    public List<Comment> getCommentsByUserId(User user){
        return commentRepository.getCommentByUserId(user.getId());
    }

    public List<Comment> getCommentsByNewId(New news){
        return commentRepository.getCommentByNewId(news.getId());
    }
}



