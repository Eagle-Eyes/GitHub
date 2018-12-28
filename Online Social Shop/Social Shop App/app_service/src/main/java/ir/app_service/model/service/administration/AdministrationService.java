package ir.app_service.model.service.administration;

import ir.app_service.model.entity.*;
import ir.app_service.model.entity.enumeration.AccountRole;
import ir.app_service.model.repository.*;
import ir.kk.app_service.model.entity.*;
import ir.kk.app_service.model.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.util.*;

@Service
@Transactional
public class AdministrationService implements UserDetailsService {

    private final static Logger logger = LoggerFactory.getLogger(AdministrationService.class);

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AccountRepository accountRepository;
    private GroupRepository groupRepository;
    private RoleRepository roleRepository;
    private ActionRepository actionRepository;
    private NaturalPersonRepository naturalPersonRepository;

    public AdministrationService(BCryptPasswordEncoder bCryptPasswordEncoder,
                                 AccountRepository accountRepository,
                                 GroupRepository groupRepository,
                                 RoleRepository roleRepository,
                                 ActionRepository actionRepository,
                                 NaturalPersonRepository naturalPersonRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.accountRepository = accountRepository;
        this.groupRepository = groupRepository;
        this.roleRepository = roleRepository;
        this.actionRepository = actionRepository;
        this.naturalPersonRepository = naturalPersonRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByName(username);
        if (account != null) {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            addActionsToGrantAuthority(account.getActions(), grantedAuthorities);
            for (Role role : account.getRoles()) {
                addActionsToGrantAuthority(role.getActions(), grantedAuthorities);
            }
            addActionFromGroup(account.getGroups(), grantedAuthorities);
            for (Role role : account.getRoles()) {
                addActionFromGroup(role.getGroups(), grantedAuthorities);
            }
            return new User(account.getName(), account.getPassword(), account.isActive(), true, true, true, grantedAuthorities);
        } else {
            throw new UsernameNotFoundException(String.format("Account '%s' not found!", username));
        }
    }

    private void addActionsToGrantAuthority(Set<Action> actions, Set<GrantedAuthority> grantedAuthorities) {
        for (Action action : actions) {
            for (String url : action.urlsArray()) {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + url.replace('/', '_')));
            }
        }
    }

    private void addActionFromGroup(Set<Group> groups, Set<GrantedAuthority> grantedAuthorities) {
        for (Group group : groups) {
            addActionsToGrantAuthority(group.getActions(), grantedAuthorities);
            addActionFromGroup(group.getGroups(), grantedAuthorities);
        }
    }

    public List<Action> getAccessibleActionList() {
        return actionRepository.findByAccessibility(true);
    }

    public void updateApplicationActions(Map<RequestMappingInfo, HandlerMethod> controllers) {
        logger.trace("App accesses are as below:");
        List<Action> currentActionsList = new ArrayList();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : controllers.entrySet()) {
            Action action = new Action();
            String displayName = entry.getValue().getBean() + "/" + entry.getValue().getMethod().getName();
            action.setDisplayName(displayName);
            action.setAccessibility(true);
            action.setUrl(entry.getKey().getPatternsCondition().getPatterns().toString());
            action.setRequestType(entry.getKey().getMethodsCondition().getMethods().toString());
            currentActionsList.add(action);
            logger.trace(action.toString());
        }
        List<Action> storedActionList = actionRepository.findAll();
        for (Action action : storedActionList) {
            action.setAccessibility(false);
            actionRepository.save(action);
        }
        for (Action action : currentActionsList) {
            if (storedActionList.contains(action)) {
                Action temp = storedActionList.get(storedActionList.indexOf(action));
                action.setId(temp.getId());
                action.setRegisteredDate(temp.getRegisteredDate());
            }
            actionRepository.save(action);
        }
    }

    public void assignFullAccessToAdminAccount(String accountName) {
        List<Action> appActionList = actionRepository.findByAccessibility(true);
        Account account = accountRepository.findByName(accountName);

        for (Action action : appActionList) {
            account.getActions().add(action);
        }
    }

    public void initializeSystemUser(String adminAccountName, String adminPassword, String adminDisplayName) {
        if (!isAccountExist(adminAccountName)) {
            logger.trace("initialize admin user ...");
            Account account = new Account();
            account.setName(adminAccountName);
            account.setDisplayName(adminDisplayName);
            account.setPassword(adminPassword);
            saveAccount(account);
        }
    }

    public void assignAccountActions(String accountName, String... actionsNames) {
        Account account = accountRepository.findByName(accountName);
        Set<Action> newActions = account.getActions();
        for (String actionName : actionsNames) {
            Action action = actionRepository.findByDisplayName(actionName);
            newActions.add(action);
        }
        for (Action newAction : newActions) {
            account.getActions().add(newAction);
        }
    }

    public boolean isAccountExist(String name) {
        boolean count = accountRepository.countByName(name) > 0;
        return count;
    }

    public Set<Account> listAccounts() {
        return new HashSet<>(accountRepository.findAll());
    }

    public Set<NaturalPerson> listPersons() {
        return new HashSet<>(naturalPersonRepository.findAll());
    }

    public Set<Group> listGroups() {
        return new HashSet<>(groupRepository.findAll());
    }

    public Set<Role> listRoles() {
        return new HashSet<>(roleRepository.findAll());
    }

    public Set<Action> listActions() {
        return new HashSet<>(actionRepository.findAll());
    }

    public Account saveAccount(Account account) {
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public Action saveAction(Action action) {
        return actionRepository.save(action);
    }

    public NaturalPerson savePerson(NaturalPerson person) {
        return naturalPersonRepository.save(person);
    }

    public void removeAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public void removeGroup(Long id) {
        groupRepository.deleteById(id);
    }

    public void removeRole(Long id) {
        roleRepository.deleteById(id);
    }

    public void removeAction(Long id) {
        actionRepository.deleteById(id);
    }

    public void removePerson(Long id) {
        naturalPersonRepository.deleteById(id);
    }

    public Action findAction(Long id) {
        return actionRepository.findById(id).get();
    }

    public Account findAccount(Long id) {
        return accountRepository.findById(id).get();
    }

    public Group findGroup(Long id) {
        return groupRepository.findById(id).get();
    }

    public Role findRole(Long id) {
        return roleRepository.findById(id).get();
    }

    public Account findAccountByName(String name) {
        return accountRepository.findByName(name);
    }

    public Group findGroupByName(String name) {
        return groupRepository.findByDisplayName(name);
    }


    public Role findRoleByName(String name) {
        return roleRepository.findByDisplayName(name);
    }

    public void updateApplicationRoles(Set<AccountRole> roles) {
        for (AccountRole roleName : roles) {
            if (!roleRepository.existsByDisplayNameIgnoreCase(roleName.name())) {
                Role role = new Role();
                role.setDisplayName(roleName.name());
                roleRepository.save(role);
            }
        }
    }
}
