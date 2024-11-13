package br.com.matheusfernandes.web.service.service;

import br.com.matheusfernandes.web.service.dto.LoginDTO;
import br.com.matheusfernandes.web.service.entity.User;
import br.com.matheusfernandes.web.service.helper.CryptoHelper;
import br.com.matheusfernandes.web.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public User login(LoginDTO loginDTO) {
        try {
            // Procura o usuário
            User user = userRepository.findByEmail(loginDTO.getEmail());

            // Caso o usuário seja nulo, lançamos uma exceção
            Objects.requireNonNull(user, "Usuário nao encontrado");

            // Encripta a senha para testar
            String md5Password = CryptoHelper.generateMD5Hash(loginDTO.getPassword());

            // Testa a senha
            if (!user.getPasswordHash().equals(md5Password)) {
                throw new Exception("Senha incorreta");
            }

            // Caso tudo ocorra bem, retornamos o usuário
            return user;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
