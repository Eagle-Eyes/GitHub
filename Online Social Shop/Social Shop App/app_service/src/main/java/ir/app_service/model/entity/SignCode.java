package ir.app_service.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Setter
@Getter
public class SignCode extends BaseEntity {


    private Date expiresOn;

    private Long accountId;

    public SignCode() {
    }

    public SignCode(Long accountId, String code, Date expiresOn) {
        this.expiresOn = expiresOn;
        this.accountId = accountId;
        this.setDisplayName(code);
    }
}
