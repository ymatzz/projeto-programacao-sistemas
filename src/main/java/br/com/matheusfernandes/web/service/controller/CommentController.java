package br.com.matheusfernandes.web.service.controller;

import br.com.matheusfernandes.web.service.dto.CommentDTO;
import br.com.matheusfernandes.web.service.entity.Comment;
import br.com.matheusfernandes.web.service.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {
    
    private final CommentService commentService;

    @GetMapping
    List<Comment> list(){
        return commentService.list();
    }

    @GetMapping("/{id}")
    Comment getComment(@PathVariable("id") Long id){
        return commentService.getCommentById(id);
    }

    @PostMapping
    List<Comment> create(@RequestBody CommentDTO commentDTO){
        return commentService.create(commentDTO);
    }

    @PutMapping("/{id}")
    List<Comment> update(@PathVariable("id") Long id, @RequestBody CommentDTO comment){
        return commentService.update(comment);
    }

    @DeleteMapping("/{id}")
    List<Comment> delete(@PathVariable("id") Long id){
        return commentService.delete(id);
    }

    @GetMapping("?createdUserId={id}")
    List<Comment> getCommentsByUserId(@PathVariable("id") Long id){
        return commentService.getCommentsByUserId(id);
    }

    @GetMapping("?createdNewId={id}")
    List<Comment> getCommentsByNewId(@PathVariable("id") Long id){
        return commentService.getCommentsByNewId(id);
    }
}
