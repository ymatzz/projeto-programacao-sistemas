package br.com.matheusfernandes.todo_list.service;

import br.com.matheusfernandes.todo_list.entity.Todo;
import br.com.matheusfernandes.todo_list.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public List<Todo> getAll(){
        Sort sort = Sort.by("descricao").ascending();
        return todoRepository.findAll(sort);
    }

    public List<Todo> create(Todo todo){
        todoRepository.save(todo);
        return getAll();
    }

    public List<Todo> update(Todo todo){
        todoRepository.save(todo);
        return getAll();
    }

    public List<Todo> delete(Long id){
        todoRepository.deleteById(id);
        return getAll();
    }
}
