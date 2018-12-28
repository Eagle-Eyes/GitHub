package ir.app_service.model.service.businessLogic.registration;

import org.springframework.stereotype.Component;

@Component
public class SmsProvider {
    public void send(String number, String text) {
        System.out.println(">>>>>>>>>>>>sending sms to number : " + number + " text value : " + text);
    }
}
