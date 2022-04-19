package ro.utcluj.pandafooddelivery.service.mappper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.utcluj.pandafooddelivery.controller.dto.AdministratorDTO;
import ro.utcluj.pandafooddelivery.model.Administrator;

@Service
@AllArgsConstructor
public class AdministratorMapper implements ObjectMapper<Administrator, AdministratorDTO> {


    @Override
    public Administrator convertFromDTO(AdministratorDTO administratorDTO) {
        return new Administrator(administratorDTO.getFirstName(),
                                 administratorDTO.getLastName(),
                                 administratorDTO.getEmail(),
                                 administratorDTO.getPassword(),
                                 administratorDTO.getPhoneNumber());
    }

    @Override
    public AdministratorDTO convertToDTO(Administrator administrator) {
        return null;
    }
}
