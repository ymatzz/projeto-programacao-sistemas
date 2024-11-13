package br.com.matheusfernandes.web.service.helper;

import br.com.matheusfernandes.web.service.dto.UserDTO;
import br.com.matheusfernandes.web.service.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO mappperUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }
}
