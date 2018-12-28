package ir.app_service.model.repository.logger;

import ir.app_service.model.entity.Group;
import ir.app_service.model.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggingEventException extends CrudRepository<Group, Long> {

}