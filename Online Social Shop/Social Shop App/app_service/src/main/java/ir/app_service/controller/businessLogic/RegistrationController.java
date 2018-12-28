package ir.app_service.controller.businessLogic;

import ir.app_service.configuration.constant.Constants;
import ir.app_service.model.entity.NaturalPerson;
import ir.app_service.model.service.businessLogic.registration.RegistrationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(Constants.URL_API + Constants.URL_REGISTRATION)
public class RegistrationController {

    protected static final Logger logger = LogManager.getLogger();

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @RequestMapping(value = {Constants.URL_CUSTOMER_REGISTRATION}, method = {RequestMethod.POST}/*, consumes = MediaType.MULTIPART_FORM_DATA_VALUE*/)
    public void registerCustomer(@RequestBody NaturalPerson person) {
        registrationService.registerCustomer(person);
    }

    @RequestMapping(value = {Constants.URL_CUSTOMER_VERIFY}, method = {RequestMethod.POST}/*, consumes = MediaType.MULTIPART_FORM_DATA_VALUE*/)
    public void validateRegistration(@RequestParam String user, @RequestParam String code) {
        registrationService.validateRegistration(user, code);
    }


    @RequestMapping(value = {Constants.URL_ADVISER_REGISTRATION}, method = {RequestMethod.POST})
    public void registerAdviser(@RequestBody NaturalPerson person) {
        registrationService.registerAdviser(person);
    }


    @RequestMapping(value = {Constants.URL_SUPERVISOR_REGISTRATION}, method = {RequestMethod.POST})
    public void registerSupervisor(@RequestBody NaturalPerson person) {
        registrationService.registerSupervisor(person);
    }


    @RequestMapping(value = {Constants.URL_CHIEF_SUPERVISOR_REGISTRATION}, method = {RequestMethod.POST})
    public void registerChiefSupervisor(@RequestBody NaturalPerson person) {
        registrationService.registerChiefSupervisor(person);
    }


    @RequestMapping(value = {Constants.URL_OBSERVER_REGISTRATION}, method = {RequestMethod.POST})
    public void registerObserver(@RequestBody NaturalPerson person) {
        registrationService.registerObserver(person);
    }

}
