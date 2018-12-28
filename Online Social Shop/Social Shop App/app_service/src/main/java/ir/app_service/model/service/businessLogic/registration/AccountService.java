package ir.app_service.model.service.businessLogic.registration;

import ir.app_service.model.repository.AccountRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Set<String> getAccountRolesByName(String username) {

        Set<String> roles = new HashSet<>();
        accountRepository.findByName(username).getRoles().forEach(role -> roles.add(role.getDisplayName()));
        return roles;
    }
}
