package br.com.matheusfernandes.web.service.service;

import br.com.matheusfernandes.web.service.entity.User;
import br.com.matheusfernandes.web.service.helper.CryptoHelper;
import br.com.matheusfernandes.web.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> list() {
        try {
            Sort sort = Sort.by("name").ascending();
            return userRepository.findAll(sort);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar usuários", e);
        }
    }

    public User create(User user) {
        try {
            user.setPasswordHash(CryptoHelper.generateMD5Hash(user.getPasswordHash()));
            userRepository.save(user);
            return user;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao criar usuário", e);
        }
    }

    public List<User> update(User user) {
        try {
            if (!userRepository.existsById(user.getId())) {
                throw new NoSuchElementException("Usuário não encontrado para atualização");
            }
            userRepository.save(user);
            return list();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar usuário", e);
        }
    }

    public List<User> delete(Long id) {
        try {
            if (!userRepository.existsById(id)) {
                throw new NoSuchElementException("Usuário não encontrado para exclusão");
            }
            userRepository.deleteById(id);
            return list();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao excluir usuário", e);
        }
    }

    public User getUserById(long id) {
        try {
            return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar usuário", e);
        }
    }
}
