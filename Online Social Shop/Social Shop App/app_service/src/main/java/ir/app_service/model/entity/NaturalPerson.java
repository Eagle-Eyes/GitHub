package ir.app_service.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Setter
@Getter
public class NaturalPerson extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column
    private String phoneNumber;

    @Column
    private String mobileNumber;

    @Column
    private String address;

    @Column(length = 10)
    private String nationalId;

    @Column
    private Date birthDate;

    @OneToOne
    private Document avatar;

    @OneToOne
    private Account account;

    @Override
    public String toString() {
        return String.format("%s %s", firstName, lastName);
    }

}
