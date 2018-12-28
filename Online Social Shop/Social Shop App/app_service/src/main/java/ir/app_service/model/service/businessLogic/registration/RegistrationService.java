package ir.app_service.model.service.businessLogic.registration;

import ir.app_service.model.entity.Account;
import ir.app_service.model.entity.NaturalPerson;
import ir.app_service.model.entity.Role;
import ir.app_service.configuration.constant.Constants;
import ir.app_service.model.repository.RoleRepository;
import ir.app_service.model.service.administration.AdministrationService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;


@Service
@Transactional
public class RegistrationService {

    private AdministrationService administrationService;
    private RoleRepository roleRepository;
    private SmsService smsService;

    public RegistrationService(AdministrationService administrationService, RoleRepository roleRepository, SmsService smsService) {
        this.administrationService = administrationService;
        this.roleRepository = roleRepository;
        this.smsService = smsService;
    }

    public void registerCustomer(NaturalPerson person) {
        if (administrationService.findAccountByName(person.getAccount().getName()) == null) {
            Account account = person.getAccount();
            account.setActive(false);
            Role role = roleRepository.findByDisplayName(Constants.ROLE_CUSTOMER);
            account.setRoles(new HashSet<>());
            account.getRoles().add(role);
            account.setNaturalPerson(person);
            account = administrationService.saveAccount(account);
            role.getAccounts().add(account);
            person.setAccount(account);
            administrationService.savePerson(person);
            administrationService.saveRole(role);
        }
        smsService.generateSignCodeForAccount(person.getAccount());
    }

    public void registerAdviser(NaturalPerson person) {
        Account account = person.getAccount();
        Role role = roleRepository.findByDisplayName(Constants.ROLE_ADVISER);

        account.getRoles().add(role);
        role.getAccounts().add(account);

        account.setNaturalPerson(person);
        person.setAccount(account);

        administrationService.savePerson(person);
        administrationService.saveAccount(account);
        administrationService.saveRole(role);
    }

    public void registerSupervisor(NaturalPerson person) {
        Account account = person.getAccount();
        Role role = roleRepository.findByDisplayName(Constants.ROLE_SUPERVISOR);

        account.getRoles().add(role);
        role.getAccounts().add(account);

        account.setNaturalPerson(person);
        person.setAccount(account);

        administrationService.savePerson(person);
        administrationService.saveAccount(account);
        administrationService.saveRole(role);
    }

    public void registerChiefSupervisor(NaturalPerson person) {
        Account account = person.getAccount();
        Role role = roleRepository.findByDisplayName(Constants.ROLE_CHIEFSUPERVISOR);

        account.getRoles().add(role);
        role.getAccounts().add(account);

        account.setNaturalPerson(person);
        person.setAccount(account);

        administrationService.savePerson(person);
        administrationService.saveAccount(account);
        administrationService.saveRole(role);
    }

    public void registerObserver(NaturalPerson person) {
        Account account = person.getAccount();
        Role role = roleRepository.findByDisplayName(Constants.ROLE_OBSERVER);

        account.getRoles().add(role);
        role.getAccounts().add(account);

        account.setNaturalPerson(person);
        person.setAccount(account);

        administrationService.savePerson(person);
        administrationService.saveAccount(account);
        administrationService.saveRole(role);
    }

    public void validateRegistration(String user, String signCode) {
        Account account = administrationService.findAccountByName(user);
        if (smsService.validateSignCode(account, signCode))
            account.setActive(true);
        administrationService.saveAccount(account);
    }
}
