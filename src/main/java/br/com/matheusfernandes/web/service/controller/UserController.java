package br.com.matheusfernandes.web.service.controller;

import br.com.matheusfernandes.web.service.dto.LoginDTO;
import br.com.matheusfernandes.web.service.dto.UserDTO;
import br.com.matheusfernandes.web.service.entity.User;
import br.com.matheusfernandes.web.service.service.LoginService;
import br.com.matheusfernandes.web.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final LoginService loginService;

    @GetMapping
    List<UserDTO> list(){
        return userService.list();
    }

    @GetMapping("/{id}")
    UserDTO getUser(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    @PostMapping
    UserDTO create(@RequestBody User user){
        return userService.create(user);
    }

    @PutMapping("/{id}")
    UserDTO update(@PathVariable("id") Long id, @RequestBody User user){
        user.setId(id);
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    List<UserDTO> delete(@PathVariable("id") Long id){
        return userService.delete(id);
    }

    @PostMapping("/login")
    UserDTO login(@RequestBody LoginDTO loginData){
        return loginService.login(loginData);
    }
}
