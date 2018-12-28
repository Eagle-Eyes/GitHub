package ir.app_service.configuration;

import ir.app_service.model.entity.Action;
import ir.app_service.model.entity.enumeration.AccountRole;
import ir.app_service.model.service.administration.AdministrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class AppInitializer implements InitializingBean, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(AppInitializer.class);
    public static List<Action> appActions;

    private Environment env;
    private AdministrationService administrationService;
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    public AppInitializer(Environment env,
                          AdministrationService administrationService,
                          RequestMappingHandlerMapping requestMappingHandlerMapping) {
        this.env = env;
        this.administrationService = administrationService;
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.trace("update app actions ...");
        administrationService.updateApplicationActions(requestMappingHandlerMapping.getHandlerMethods());

        logger.trace("update app roles ...");
        administrationService.updateApplicationRoles(new HashSet(Arrays.asList(AccountRole.values())));

        String adminAccountName = env.getProperty("app.admin.accountName");
        String adminPassword = env.getProperty("app.admin.password");
        String appName = env.getProperty("app.name");
        String adminDisplayName = String.format("%s [%s App User]", adminAccountName, appName.toUpperCase());
        administrationService.initializeSystemUser(adminAccountName, adminPassword, adminDisplayName);

        logger.trace("load app actions");
        appActions = administrationService.getAccessibleActionList();

        logger.trace("assign full access to admin");
        administrationService.assignFullAccessToAdminAccount(adminAccountName);

    }

    @Override
    public void destroy() {

    }

}
