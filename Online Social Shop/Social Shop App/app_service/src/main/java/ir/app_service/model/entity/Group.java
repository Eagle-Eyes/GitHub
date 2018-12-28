package ir.app_service.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Table(name = "app_group")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Setter
@Getter
public class Group extends BaseEntity {

    @ManyToMany(mappedBy = "groups", cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Account> accounts;

    @ManyToMany(mappedBy = "groups", cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Role> roles;

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    private Set<Action> actions;

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    private Set<Group> groups;

    @ManyToMany
    private Set<Message> receivedMessages;

}
