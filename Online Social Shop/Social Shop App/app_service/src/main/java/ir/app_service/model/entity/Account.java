package ir.app_service.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Table(name = "app_account")
@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Account extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 255)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @OneToOne
    private Document avatar;

    @OneToOne(mappedBy = "account")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private NaturalPerson naturalPerson;

    @ManyToMany
    private Set<Group> groups;

    @OneToMany(mappedBy = "attendee")
    private Set<Attendee> attendees;

    @OneToMany(mappedBy = "adviser")
    private Set<PropertyFileOffer> advisingPropertyFileOffers;

    @OneToMany(mappedBy = "registrar")
    private Set<PropertyFileOffer> registeredPropertyFileOffers;

    @OneToMany(mappedBy = "sender")
    private Set<Message> sentMessages;

    @ManyToMany()
    private Set<Message> receivedMessages;

    @ManyToMany
    private Set<Role> roles;

    @ManyToMany
    private Set<Action> actions;

    @OneToMany(mappedBy = "adviser")
    private Set<PropertyFileFindRequest> advisingPropertyFileFindRequests;

    @OneToMany(mappedBy = "searcher")
    private Set<PropertyFileFindRequest> searchingPropertyFileFindRequests;

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
