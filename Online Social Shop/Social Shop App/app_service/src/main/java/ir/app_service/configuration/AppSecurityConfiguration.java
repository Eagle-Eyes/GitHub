package ir.app_service.configuration;

import ir.app_service.configuration.Handler.ResponseAuthenticationHandler;
import ir.app_service.configuration.constant.Constants;
import ir.app_service.model.entity.Action;
import ir.app_service.model.service.administration.AdministrationService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@EnableWebSecurity
public class AppSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String RESOURCE_URL = "/resources/**";
    private static final List<String> CORS_URLS = Collections.singletonList("*");
    private static final List<String> CORS_METHODS = Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH");
    private static final List<String> CORS_HEADERS = Arrays.asList("Authorization", "Cache-Control", "Content-Type");

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AdministrationService administrationService;
    private ResponseAuthenticationHandler responseAuthenticationHandler;

    public AppSecurityConfiguration(BCryptPasswordEncoder bCryptPasswordEncoder,
                                    AdministrationService administrationService,
                                    ResponseAuthenticationHandler responseAuthenticationHandler) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.administrationService = administrationService;
        this.responseAuthenticationHandler = responseAuthenticationHandler;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.unmodifiableList(CORS_URLS));
        configuration.setAllowedMethods(Collections.unmodifiableList(CORS_METHODS));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Collections.unmodifiableList(CORS_HEADERS));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(administrationService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // TODO: 12/5/18 enable csrf field
        http.authorizeRequests().
                antMatchers(String.format("%s%s", Constants.URL_MANAGEMENT, Constants.URL_PUBLIC_LOGIN),
                        String.format("%s%s", Constants.URL_MANAGEMENT, Constants.URL_CHANGE_LANGUAGE),
                        RESOURCE_URL).permitAll()
//                .and().authorizeRequests().antMatchers(String.format("%s/*", Constants.URL_MANAGEMENT)).denyAll().and()
                .and()
                .formLogin().defaultSuccessUrl(Constants.URL_MANAGEMENT, true)
                .loginPage(String.format("%s%s", Constants.URL_MANAGEMENT, Constants.URL_PUBLIC_LOGIN))
                .loginProcessingUrl(Constants.URL_LOGIN_ACTION)
                .successHandler(responseAuthenticationHandler)
                .failureUrl(String.format("%s?signin=error", String.format("%s%s", Constants.URL_MANAGEMENT, Constants.URL_PUBLIC_LOGIN)))
                .permitAll()
                .and()
                .logout().logoutUrl(String.format("%s%s", Constants.URL_MANAGEMENT, Constants.URL_PUBLIC_LOGOUT))
                .and()
                .cors()
                .and()
                .csrf().disable()
                .headers().frameOptions().sameOrigin();

        for (Action action : AppInitializer.appActions) {
            for (String url : action.urlsArray()) {
                http.authorizeRequests().antMatchers(url + "**").hasRole(url.replace('/', '_'));
            }
        }
    }
}
