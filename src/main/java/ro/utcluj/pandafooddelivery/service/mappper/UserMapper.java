package ro.utcluj.pandafooddelivery.service.mappper;

import org.springframework.stereotype.Service;
import ro.utcluj.pandafooddelivery.controller.dto.UserDTO;
import ro.utcluj.pandafooddelivery.model.User;

@Service
public class UserMapper implements ObjectMapper<User, UserDTO> {

    @Override
    public User convertFromDTO(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getEmail(),user.getUserType());
    }
}
