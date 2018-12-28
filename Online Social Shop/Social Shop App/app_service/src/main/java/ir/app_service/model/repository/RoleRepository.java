package ir.app_service.model.repository;

import ir.app_service.model.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByDisplayName(String name);

    boolean existsByDisplayNameIgnoreCase(String displayName);

}