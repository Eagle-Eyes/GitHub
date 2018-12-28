package ir.app_service.controller.admin;

import ir.app_service.configuration.constant.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(Constants.URL_MANAGEMENT)
public class LoginController {

    @RequestMapping(value = {Constants.URL_PUBLIC_LOGIN}, method = {RequestMethod.GET})
    public ModelAndView login(ModelAndView mav) {
        mav.setViewName("adminUI/login");
        return mav;
    }
}
