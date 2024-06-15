package br.com.matheusfernandes.todo_list.controller;

import br.com.matheusfernandes.todo_list.entity.Todo;
import br.com.matheusfernandes.todo_list.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    List<Todo> list(){
        return todoService.list();
    }

    @PostMapping
    List<Todo> create(@RequestBody Todo todo){
    return todoService.create(todo);
    }

    @PutMapping
    List<Todo> update(@RequestBody Todo todo){
        return todoService.update(todo);
    }

    @DeleteMapping("{id}")
    List<Todo> delete(@PathVariable("id") Long id){
        return todoService.delete(id);
    }
}
