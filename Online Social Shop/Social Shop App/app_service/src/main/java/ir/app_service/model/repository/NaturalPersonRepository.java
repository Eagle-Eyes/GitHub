package ir.app_service.model.repository;

import ir.app_service.model.entity.NaturalPerson;
import org.springframework.stereotype.Repository;

@Repository
public interface NaturalPersonRepository extends CrudRepository<NaturalPerson, Long> {

}