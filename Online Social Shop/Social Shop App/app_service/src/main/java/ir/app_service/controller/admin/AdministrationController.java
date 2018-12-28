package ir.app_service.controller.admin;

import ir.app_service.configuration.constant.Constants;
import ir.app_service.model.entity.Account;
import ir.app_service.model.entity.Action;
import ir.app_service.model.entity.Group;
import ir.app_service.model.entity.Role;
import ir.app_service.model.service.administration.AdministrationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Mohsen Rahimloye
 */

@Controller
@RequestMapping(Constants.URL_MANAGEMENT)
public class AdministrationController {

    protected static final Logger logger = LogManager.getLogger();

    private AdministrationService administrationService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdministrationController(AdministrationService administrationService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.administrationService = administrationService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(value = {Constants.URL_LIST_ACCOUNTS}, method = {RequestMethod.GET})
    public ModelAndView listAccount(ModelAndView mav) {
        mav = new ModelAndView("manageAccounts");
        Set<Account> accounts = administrationService.listAccounts();
        mav.addObject("accountRepo", accounts);
        return mav;
    }

    @RequestMapping(value = {Constants.URL_LIST_GROUPS}, method = {RequestMethod.GET})
    public ModelAndView listGroups(ModelAndView mav) {
        mav = new ModelAndView("manageGroups");
        Set<Group> groups = administrationService.listGroups();
        mav.addObject("groupRepo", groups);
        return mav;
    }

    @RequestMapping(value = {Constants.URL_LIST_ROLES}, method = {RequestMethod.GET})
    public ModelAndView listRoles(ModelAndView mav) {
        mav = new ModelAndView("manageRoles");
        Set<Role> roles = administrationService.listRoles();
        mav.addObject("roleRepo", roles);
        return mav;
    }

    @RequestMapping(value = {Constants.URL_LIST_ACTIONS}, method = {RequestMethod.GET})
    public ModelAndView listActions(ModelAndView mav) {
        mav.setViewName("manageActions");
        Set<Action> actions = administrationService.listActions();
        mav.addObject("actionRepo", actions);
        return mav;
    }

    @RequestMapping(value = {Constants.URL_ADD_ACCOUNT})
    public ModelAndView addNewAccount(ModelAndView mav) {
        mav.setViewName("addAccount");
        return mav;
    }

    @RequestMapping(value = {Constants.URL_SAVE_ACCOUNT}, method = {RequestMethod.POST})
    public ModelAndView saveAccount(@RequestParam String name, @RequestParam String password, ModelAndView mav) {
        if (administrationService.findAccountByName(name) == null) {
            Account account = new Account();
            account.setName(name);
            account.setDisplayName(name);
            account.setPassword(password);
            account.setRegisteredDate(new Date());
            administrationService.saveAccount(account);
            mav.addObject("message", "Saved_successfully");
            mav.addObject("accountRepo", administrationService.listAccounts());
            mav.setViewName("manageAccounts");
        } else {
            mav.addObject("message", "Name_already_exists");
            mav.addObject("enteredValue", name);
            mav.setViewName("addAccount");
        }
        return mav;
    }

    @RequestMapping(value = {Constants.URL_EDIT_ACCOUNT})
    public ModelAndView editAccount(ModelAndView mav, Account account) {
        mav.setViewName("listAccount");
        administrationService.saveAccount(account);
        Set<Account> accounts = administrationService.listAccounts();
        mav.addObject("accountRepo", accounts);
        return mav;
    }

    @RequestMapping(value = {Constants.URL_REMOVE_ACCOUNT}, method = {RequestMethod.GET})
    public ModelAndView removeAccount(ModelAndView mav, @PathVariable("accountId") Long accountId) {
        administrationService.removeAccount(accountId);
        Set<Account> accounts = administrationService.listAccounts();
        mav.addObject("accountRepo", accounts);
        mav.addObject("message", "Deleted_successfully");
        mav.setViewName("manageAccounts");
        return mav;
    }

    @RequestMapping(value = {Constants.URL_ADD_GROUP}, method = {RequestMethod.GET})
    public ModelAndView addNewGroup(ModelAndView mav) {
        mav.setViewName("addGroup");
        return mav;
    }

    @RequestMapping(value = {Constants.URL_SAVE_GROUP}, method = {RequestMethod.POST})
    public ModelAndView saveGroup(@RequestParam String displayName, ModelAndView mav) {
        if (administrationService.findGroupByName(displayName) == null) {
            Group group = new Group();
            group.setDisplayName(displayName);
            group.setRegisteredDate(new Date());
            administrationService.saveGroup(group);
            mav.addObject("message", "Saved_successfully");
            mav.addObject("groupRepo", administrationService.listGroups());
            mav.setViewName("manageGroups");
        } else {
            mav.addObject("message", "Name_already_exists");
            mav.addObject("enteredValue", displayName);
            mav.setViewName("addGroup");
        }
        return mav;
    }

    @RequestMapping(value = {Constants.URL_EDIT_GROUP})
    public ModelAndView editGroup(ModelAndView mav, Group group) {
        mav.setViewName("listGroup");
        administrationService.saveGroup(group);
        Set<Group> groups = administrationService.listGroups();
        mav.addObject("groupRepo", groups);
        return mav;
    }

    @RequestMapping(value = {Constants.URL_REMOVE_GROUP}, method = {RequestMethod.GET})
    public ModelAndView removeGroup(ModelAndView mav, @PathVariable("groupId") Long groupId) {
        mav.setViewName("manageGroups");
        administrationService.removeGroup(groupId);
        Set<Group> groups = administrationService.listGroups();
        mav.addObject("groupRepo", groups);
        return mav;
    }

    @RequestMapping(value = {Constants.URL_ADD_ROLE}, method = {RequestMethod.GET})
    public ModelAndView addNewRole(ModelAndView mav) {
        mav.setViewName("addRole");
        return mav;
    }

    @RequestMapping(value = {Constants.URL_SAVE_ROLE}, method = {RequestMethod.POST})
    public ModelAndView saveRole(ModelAndView mav, @RequestParam String displayName) {
        if (administrationService.findRoleByName(displayName) == null) {
            Role role = new Role();
            role.setDisplayName(displayName);
            role.setRegisteredDate(new Date());
            administrationService.saveRole(role);
            mav.addObject("message", "Saved_successfully");
            mav.setViewName(String.format("redirect:%s%s", Constants.URL_MANAGEMENT, Constants.URL_LIST_ROLES));
        } else {
            mav.addObject("message", "Name_already_exists");
            mav.addObject("enteredValue", displayName);
            mav.setViewName("addRole");
        }
        return mav;
    }

    @RequestMapping(value = {Constants.URL_EDIT_ROLE})
    public ModelAndView editRole(ModelAndView mav, Role role) {
        mav.setViewName("listRole");
        administrationService.saveRole(role);
        Set<Role> roles = administrationService.listRoles();
        mav.addObject("roleRepo", roles);
        return mav;
    }

    @RequestMapping(value = {Constants.URL_REMOVE_ROLE}, method = {RequestMethod.GET})
    public ModelAndView removeRole(ModelAndView mav, @PathVariable("roleId") Long roleId) {
        administrationService.removeRole(roleId);
        Set<Role> rolls = administrationService.listRoles();
        mav.addObject("roleRepo", rolls);
        mav.addObject("message", "Deleted_successfully");
        mav.setViewName("manageRoles");
        return mav;
    }

    @RequestMapping(value = {Constants.URL_REMOVE_ACTION}, method = {RequestMethod.DELETE})
    public ModelAndView removeAction(ModelAndView mav, @PathVariable Action action) {
        Action actionRemoved = administrationService.findAction(action.getId());
        if (!actionRemoved.accessibility) {
            administrationService.removeAction(actionRemoved.getId());
            mav.addObject("message", "Deleted_successfully");
        } else {
            mav.addObject("message", "Accessibility_is_false!");
        }
        Set<Action> actions = administrationService.listActions();
        mav.addObject("actionRepo", actions);
        mav.setViewName("manageActions");
        return mav;
    }

    @RequestMapping(value = {Constants.URL_CHANGE_LANGUAGE}, method = {RequestMethod.GET})
    public String changeLanguage(HttpServletRequest request, @RequestParam String lang) {
        request.getSession().setAttribute("dir", lang.equals("en") ? "ltr" : "rtl");
        request.getSession().setAttribute("lang", lang);
        String path = request.getUserPrincipal() == null ? "login" : "index";
        return "adminUI/" + path;
    }

    @RequestMapping(method = {RequestMethod.GET})
    public String index_page(HttpServletRequest request) {
        if (request.getSession().getAttribute("name") == null)
            request.getSession().setAttribute("name", request.getUserPrincipal().getName());
        return "adminUI/index";
    }

    @RequestMapping(value = {Constants.URL_LIST_ACCOUNTS_ACTIONS}, method = {RequestMethod.GET})
    public String actionToAccount(ModelMap model) {
        model.put("actionRepo", administrationService.listActions());
        model.put("accountRepo", administrationService.listAccounts());
        return "actionToAccount";
    }

    @RequestMapping(value = {Constants.URL_ACCOUNT_ACTIONS}, method = {RequestMethod.GET})
    public String actionToAccountAdd(Account account, ModelMap model) {
        if (account.getId() != 0) {
            Account selectedAccount = administrationService.findAccount(account.getId());
            List<String> actionlist = new ArrayList<>();
            for (Action ac : selectedAccount.getActions()) {
                actionlist.add(Long.toString(ac.getId()));
            }
            model.put("selectedaccount", selectedAccount);
            model.put("accountActionIds", actionlist);
        }
        model.put("accountRepo", administrationService.listAccounts());
        model.put("actionRepo", administrationService.listActions());
        return "actionToAccount";
    }

    @RequestMapping(value = {Constants.URL_LIST_ACCOUNTS_ACTIONS}, method = {RequestMethod.POST})
    public String saveActionsToAccount(@RequestParam Long id, @RequestParam String[] actions, ModelMap model) {
        Account selectedAccount = administrationService.findAccount(id);
        selectedAccount.getActions().clear();
        for (String accountAction : actions) {
            if (accountAction.equals("0"))
                continue;
            Action action = administrationService.findAction(Long.parseLong(accountAction));
            selectedAccount.getActions().add(action);
            action.getAccounts().add(selectedAccount);
            administrationService.saveAction(action);
        }
        administrationService.saveAccount(selectedAccount);
        model.put("accountRepo", administrationService.listAccounts());
        model.put("actionRepo", administrationService.listActions());
        model.put("message", "Updated_successfully");
        return "actionToAccount";
    }

    @RequestMapping(value = {Constants.URL_LIST_ACCOUNTS_ROLES}, method = {RequestMethod.GET})
    public String roleToAccount(ModelMap model) {
        model.put("roleRepo", administrationService.listRoles());
        model.put("accountRepo", administrationService.listAccounts());
        return "roleToAccount";
    }

    @RequestMapping(value = {Constants.URL_ACCOUNT_ROLES}, method = {RequestMethod.GET})
    public String roleToAccountAdd(Account account, ModelMap model) {
        if (account.getId() != 0) {
            Account selectedAccount = administrationService.findAccount(account.getId());
            List<String> rolelist = new ArrayList<>();
            for (Role r : selectedAccount.getRoles()) {
                rolelist.add(Long.toString(r.getId()));
            }
            model.put("selectedaccount", selectedAccount);
            model.put("accountroleids", rolelist);
        }
        model.put("accountRepo", administrationService.listAccounts());
        model.put("roleRepo", administrationService.listRoles());
        return "roleToAccount";
    }

    @RequestMapping(value = {Constants.URL_LIST_ACCOUNTS_ROLES}, method = {RequestMethod.POST})
    public String saveRoleToAccount(@RequestParam Long id, @RequestParam String[] roles, ModelMap model) {
        Account selectedAccount = administrationService.findAccount(id);
        selectedAccount.getRoles().clear();
        for (String role : roles) {
            if (role.equals("0"))
                continue;
            Role role1 = administrationService.findRole(Long.parseLong(role));
            selectedAccount.getRoles().add(role1);
            role1.getAccounts().add(selectedAccount);
            administrationService.saveRole(role1);
        }
        administrationService.saveAccount(selectedAccount);
        model.put("accountRepo", administrationService.listAccounts());
        model.put("roleRepo", administrationService.listRoles());
        model.put("message", "Updated_successfully");
        return "roleToAccount";
    }

    @RequestMapping(value = {Constants.URL_LIST_ACCOUNTS_GROUPS}, method = {RequestMethod.GET})
    public String groupToAccount(ModelMap model) {
        model.put("groupRepo", administrationService.listGroups());
        model.put("accountRepo", administrationService.listAccounts());
        return "groupToAccount";
    }

    @RequestMapping(value = {Constants.URL_ACCOUNT_GROUPS}, method = {RequestMethod.GET})
    public String groupToAccountAdd(Account account, ModelMap model) {
        Account selectedAccount = administrationService.findAccount(account.getId());
        List<String> grouplist = new ArrayList<>();
        for (Group r : selectedAccount.getGroups()) {
            grouplist.add(Long.toString(r.getId()));
        }
        model.put("selectedaccount", selectedAccount);
        model.put("accountgroupids", grouplist);
        model.put("accountRepo", administrationService.listAccounts());
        model.put("groupRepo", administrationService.listGroups());
        return "groupToAccount";
    }

    @RequestMapping(value = {Constants.URL_LIST_ACCOUNTS_GROUPS}, method = {RequestMethod.POST})
    public String saveGroupToAccount(@RequestParam Long id, @RequestParam String[] groups, ModelMap model) {
        Account selectedAccount = administrationService.findAccount(id);
        selectedAccount.getGroups().clear();
        for (String group : groups) {
            if (group.equals("0"))
                continue;
            Group group1 = administrationService.findGroup(Long.parseLong(group));
            selectedAccount.getGroups().add(group1);
            group1.getAccounts().add(selectedAccount);
            administrationService.saveGroup(group1);
        }
        administrationService.saveAccount(selectedAccount);
        model.put("accountRepo", administrationService.listAccounts());
        model.put("groupRepo", administrationService.listGroups());
        model.put("message", "Updated_successfully");
        return "groupToAccount";
    }

    @RequestMapping(value = {Constants.URL_LIST_GROUPS_ACTIONS}, method = {RequestMethod.GET})
    public String actionToGroup(ModelMap model) {
        model.put("actionRepo", administrationService.listActions());
        model.put("groupRepo", administrationService.listGroups());
        return "actionToGroup";
    }

    @RequestMapping(value = {Constants.URL_GROUP_ACTIONS}, method = {RequestMethod.GET})
    public String actionToGroupAdd(Group group, ModelMap model) {
        Group selectedGroup = administrationService.findGroup(group.getId());
        List<String> actionlist = new ArrayList<>();
        for (Action ac : selectedGroup.getActions()) {
            actionlist.add(Long.toString(ac.getId()));
        }
        model.put("selectedgroup", selectedGroup);
        model.put("groupActionIds", actionlist);
        model.put("groupRepo", administrationService.listGroups());
        model.put("actionRepo", administrationService.listActions());
        return "actionToGroup";
    }

    @RequestMapping(value = {Constants.URL_LIST_GROUPS_ACTIONS}, method = {RequestMethod.POST})
    public String saveActionToGroup(@RequestParam Long id, @RequestParam String[] actions, ModelMap model) {
        Group selectedGroup = administrationService.findGroup(id);
        selectedGroup.getActions().clear();
        for (String groupAction : actions) {
            if (groupAction.equals("0"))
                continue;
            Action action = administrationService.findAction(Long.parseLong(groupAction));
            selectedGroup.getActions().add(action);
            action.getGroups().add(selectedGroup);
            administrationService.saveAction(action);
        }
        administrationService.saveGroup(selectedGroup);
        model.put("groupRepo", administrationService.listGroups());
        model.put("actionRepo", administrationService.listActions());
        model.put("message", "Updated_successfully");
        return "actionToGroup";
    }

    @RequestMapping(value = {Constants.URL_LIST_ROLES_ACTIONS}, method = {RequestMethod.GET})
    public String actionToRole(ModelMap model) {
        model.put("actionRepo", administrationService.listActions());
        model.put("roleRepo", administrationService.listRoles());
        return "actionToRole";
    }

    @RequestMapping(value = {Constants.URL_ROLE_ACTIONS}, method = {RequestMethod.GET})
    public String actionToRoleAdd(Role role, ModelMap model) {
        Role selectedRole = administrationService.findRole(role.getId());
        List<String> actionlist = new ArrayList<>();
        for (Action ac : selectedRole.getActions()) {
            actionlist.add(Long.toString(ac.getId()));
        }
        model.put("selectedrole", selectedRole);
        model.put("roleActionIds", actionlist);
        model.put("roleRepo", administrationService.listRoles());
        model.put("actionRepo", administrationService.listActions());
        return "actionToRole";
    }

    @RequestMapping(value = {Constants.URL_LIST_ROLES_ACTIONS}, method = {RequestMethod.POST})
    public String saveActionToRole(@RequestParam Long id, @RequestParam String[] actions, ModelMap model) {
        Role selectedRole = administrationService.findRole(id);
        selectedRole.getActions().clear();
        for (String roleAction : actions) {
            if (roleAction.equals("0"))
                continue;
            Action action = administrationService.findAction(Long.parseLong(roleAction));
            selectedRole.getActions().add(action);
            action.getRoles().add(selectedRole);
            administrationService.saveAction(action);
        }
        administrationService.saveRole(selectedRole);
        model.put("roleRepo", administrationService.listRoles());
        model.put("actionRepo", administrationService.listActions());
        model.put("message", "Updated_successfully");
        return "actionToRole";
    }

    @RequestMapping(value = {Constants.URL_EDIT_PASSWORD}, method = {RequestMethod.POST})
    public String editPassword(@RequestParam String accountname, @RequestParam String oldPassword, @RequestParam String password,
                               ModelMap model) {
        Account account = administrationService.findAccountByName(accountname);
        if (bCryptPasswordEncoder.matches(oldPassword, account.getPassword())) {
            account.setPassword(password);
            administrationService.saveAccount(account);
            model.put("message", "Updated_successfully");
            model.put("accountRepo", administrationService.listAccounts());
            return "manageAccounts";
        } else {
            model.put("username", accountname);
            model.put("message", "Password_is_not_correct");
            return "changePass";
        }
    }

    @RequestMapping(value = {Constants.URL_EDIT_PASSWORD_LOAD}, method = {RequestMethod.GET})
    public String loadEditPassword(@RequestParam String name, ModelMap model) {
        model.put("accountname", name);
        return "changePass";
    }

    @RequestMapping(value = {Constants.URL_RESET_PASSWORD}, method = {RequestMethod.GET})
    public String resetPassword(@PathVariable Long id, ModelMap model) {
        Account account = administrationService.findAccount(id);
        account.setPassword(bCryptPasswordEncoder.encode("12345"));
        administrationService.saveAccount(account);
        model.put("message", "Password_reseted_successfully");
        model.put("accountRepo", administrationService.listAccounts());
        return "manageAccounts";
    }
}
