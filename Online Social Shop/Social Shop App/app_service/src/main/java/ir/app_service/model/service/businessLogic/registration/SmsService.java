package ir.app_service.model.service.businessLogic.registration;


import ir.app_service.model.entity.Account;
import ir.app_service.model.entity.SignCode;
import ir.app_service.model.repository.SignCodeRepository;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class SmsService {

    private SignCodeRepository signCodeRepository;
    private SmsProvider smsProvider;

    public SmsService(SignCodeRepository signCodeRepository, SmsProvider smsProvider) {
        this.signCodeRepository = signCodeRepository;
        this.smsProvider = smsProvider;
    }

    public void generateSignCodeForAccount(Account account) {
        signCodeRepository.deleteByAccountId(account.getId());
        String code = "";
        for (int i = 0; i < 5; i++)
            code += String.valueOf((int) (Math.random() * 10));
        signCodeRepository.save(new SignCode(account.getId(), code, new Date(Calendar.getInstance().getTimeInMillis() + (2 * 60000))));
        smsProvider.send(account.getNaturalPerson().getMobileNumber(), code);
    }

    public boolean validateSignCode(Account account, String code) {
        SignCode signCode = signCodeRepository.findByAccountId(account.getId());
        if (signCode.getExpiresOn().after(new Date()) && signCode.getDisplayName().equals(code))
            return true;
        return false;
    }
}
