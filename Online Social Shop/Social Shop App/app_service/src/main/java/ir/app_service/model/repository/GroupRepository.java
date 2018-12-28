package ir.app_service.model.repository;

import ir.app_service.model.entity.Group;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {
    Group findByDisplayName(String name);

}