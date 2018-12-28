package ir.app_service.configuration.constant;


public class Constants {

    public static final String URL_MANAGEMENT = "/admin";
    public static final String URL_PUBLIC_LOGIN = "/login";
    public static final String URL_PUBLIC_LOGOUT = "/logout";
    public static final String URL_LOGIN_ACTION = "/loginAction";

    public static final String URL_API = "/api";
    public static final String URL_REGISTRATION = "/registration";
    public static final String URL_CUSTOMER_REGISTRATION = "/customer";
    public static final String URL_CUSTOMER_VERIFY = "/customer/verify";

    public static final String URL_ADVISER_REGISTRATION = "/adviser";
    public static final String URL_SUPERVISOR_REGISTRATION = "/supervisor";
    public static final String URL_CHIEF_SUPERVISOR_REGISTRATION = "/chiefSupervisor";
    public static final String URL_OBSERVER_REGISTRATION = "/observer";

    public static final String URL_LIST_ACCOUNTS = "/accounts";
    public static final String URL_LIST_ACCOUNTS_ACTIONS = "/accounts/actions";
    public static final String URL_LIST_ACCOUNTS_ROLES = "/accounts/roles";
    public static final String URL_LIST_ACCOUNTS_GROUPS = "/accounts/groups";
    public static final String URL_LIST_GROUPS_ACTIONS = "/groups/actions";
    public static final String URL_LIST_ROLES_ACTIONS = "/roles/actions";
    public static final String URL_ACCOUNT_ACTIONS = "/accounts/accountActions";
    public static final String URL_GROUP_ACTIONS = "/groups/groupActions";
    public static final String URL_ROLE_ACTIONS = "/roles/roleActions";
    public static final String URL_ACCOUNT_ROLES = "/accounts/accountRoles";
    public static final String URL_ACCOUNT_GROUPS = "/accounts/accountGroups";
    public static final String URL_LIST_GROUPS = "/groups";
    public static final String URL_LIST_ROLES = "/roles";
    public static final String URL_LIST_ACTIONS = "/actions";
    public static final String URL_ADD_ACCOUNT = "/addAccount";
    public static final String URL_SAVE_ACCOUNT = "/accounts";
    public static final String URL_REMOVE_ACCOUNT = "/accounts/{accountId}/delete";
    public static final String URL_ADD_GROUP = "/addGroup";
    public static final String URL_SAVE_GROUP = "/groups";
    public static final String URL_REMOVE_GROUP = "/groups/{groupId}/delete";
    public static final String URL_ADD_ROLE = "/addRole";
    public static final String URL_SAVE_ROLE = "/roles";
    public static final String URL_REMOVE_ROLE = "/roles/{roleId}/delete";
    public static final String URL_REMOVE_ACTION = "/actions/{action}";
    public static final String URL_CHANGE_LANGUAGE = "/changeLanguage";
    public static final String URL_EDIT_PASSWORD = "/editPassword";
    public static final String URL_RESET_PASSWORD = "/accounts/{id}/resetPass";
    public static final String URL_EDIT_PASSWORD_LOAD = "/editPasswordLoad";

    public static final String URL_EDIT_ACCOUNT = "/accounts/edit/";
    public static final String URL_EDIT_GROUP = "/groups/edit/";
    public static final String URL_EDIT_ROLE = "/roles/edit/";

    public static final String ROLE_CUSTOMER = "customer";
    public static final String ROLE_ADVISER = "adviser";
    public static final String ROLE_SUPERVISOR = "supervisor";
    public static final String ROLE_CHIEFSUPERVISOR = "chiefSupervisor";
    public static final String ROLE_OBSERVER = "observer";

}
