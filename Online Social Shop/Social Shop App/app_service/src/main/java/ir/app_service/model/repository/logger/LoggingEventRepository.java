package ir.app_service.model.repository.logger;

import ir.app_service.model.entity.Group;
import ir.app_service.model.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggingEventRepository extends CrudRepository<Group, Long> {

}