package ir.app_service.model.repository;

import ir.app_service.model.entity.Attendee;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendeeRepository extends CrudRepository<Attendee, Long> {
}
