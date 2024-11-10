package br.com.matheusfernandes.web.service.controller;

import br.com.matheusfernandes.web.service.entity.User;
import br.com.matheusfernandes.web.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    List<User> list(){
        return userService.list();
    }

    @GetMapping("/{id}")
    User getUser(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    @PostMapping
    User create(@RequestBody User user){
        return userService.create(user);
    }

    @PutMapping("/{id}")
    List<User> update(@PathVariable("id") Long id, @RequestBody User user){
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    List<User> delete(@PathVariable("id") Long id){
        return userService.delete(id);
    }
}
