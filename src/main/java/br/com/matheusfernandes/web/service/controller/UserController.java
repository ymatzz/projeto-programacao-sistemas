package br.com.matheusfernandes.web.service.controller;

import br.com.matheusfernandes.web.service.entity.User;
import br.com.matheusfernandes.web.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    public UserController(UserService userService){
        this.userService = userService;
    }

    private UserService userService;

    @GetMapping
    List<User> list(){
        return userService.list();
    }

    @GetMapping("/{id}")
    User getUser(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    @PostMapping
    List<User> create(@RequestBody User user){
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
