package ir.app_service.model.repository;

import ir.app_service.model.entity.SignCode;
import org.springframework.stereotype.Repository;

@Repository
public interface SignCodeRepository extends CrudRepository<SignCode, Long> {

    SignCode findByAccountId(Long id);

    void deleteByAccountId(Long id);

}