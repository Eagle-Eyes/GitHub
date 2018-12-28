package ir.app_service.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Table(name = "app_role")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Setter
@Getter
public class Role extends BaseEntity {

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToMany(mappedBy = "roles", cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    private Set<Account> accounts;

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    private Set<Group> groups;

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    private Set<Action> actions;

    @ManyToMany
    private Set<Message> receivedMessages;
}
