package br.com.matheusfernandes.web.service.service;

import br.com.matheusfernandes.web.service.entity.User;
import br.com.matheusfernandes.web.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;

    public List<User> list(){
        Sort sort = Sort.by("name").ascending();
        return userRepository.findAll(sort);
    }

    public List<User> create(User user){
        userRepository.save(user);
        return list();
    }

    public List<User> update(User user){
        userRepository.save(user);
        return list();
    }

    public List<User> delete(Long id){
        userRepository.deleteById(id);
        return list();
    }

    public User getUserById(long id){
        return userRepository.getReferenceById(id);
    }
}
