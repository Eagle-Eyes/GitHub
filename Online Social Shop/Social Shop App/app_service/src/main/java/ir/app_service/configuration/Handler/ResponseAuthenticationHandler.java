package ir.app_service.configuration.Handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.app_service.model.service.businessLogic.registration.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;


@Component
public class ResponseAuthenticationHandler implements AuthenticationSuccessHandler {

    @Autowired
    private AccountService accountService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Set<String> accountRoles = accountService.getAccountRolesByName(username);

        HashMap<String, Set<String>> responseBody = new HashMap<>();

        responseBody.put("ROLES", accountRoles);
        response.setStatus(HttpStatus.OK.value());
        String json = new ObjectMapper().writeValueAsString(responseBody);
        response.getWriter().write(json);
        response.flushBuffer();
    }
}
