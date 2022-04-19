package ro.utcluj.pandafooddelivery.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ro.utcluj.pandafooddelivery.model.UserType;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {

    private final Long id;
    private final String email;
    private  String password;
    private final UserType userType;

    public UserDTO(Long id, String email, UserType userType) {
        this.id = id;
        this.email = email;
        this.userType = userType;
    }


}
