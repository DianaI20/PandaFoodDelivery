package ro.utcluj.pandafooddelivery.service.mappper;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

public interface ObjectMapper<T,S> {

    T convertFromDTO(S s);
    S convertToDTO(T t);
}
