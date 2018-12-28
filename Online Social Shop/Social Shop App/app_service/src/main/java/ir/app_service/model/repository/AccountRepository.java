package ir.app_service.model.repository;

import ir.app_service.model.entity.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    int countByName(String name);

    Account findByName(String name);

}