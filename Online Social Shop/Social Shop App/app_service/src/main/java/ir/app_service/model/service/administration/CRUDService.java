package ir.app_service.model.service.administration;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CRUDService<E> {

    ResponseEntity read(PageRequest pageRequest);

    ResponseEntity create(E entity, List<String> errorMessages);

    ResponseEntity update(E entity, List<String> errorMessages);

    // note for programmers: always check owner of item
    ResponseEntity remove(Long id);
}
