package ir.app_service.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "app_action")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Setter
@Getter
public class Action extends BaseEntity {

    @Column
    public boolean accessibility;

    @Column
    private String url;

    @Column
    private String tag;

    @Column
    private String requestType;

    @ManyToMany(mappedBy = "actions", cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Account> accounts;

    @ManyToMany(mappedBy = "actions", cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Group> groups;

    @ManyToMany(mappedBy = "actions", cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Role> roles;

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append((getUrl() + getRequestType() + getDisplayName()).toCharArray()).hashCode();
    }

    /**
     * WARNING: Object o fileds has bean changed by this method
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;

        if (o == this)
            return true;

        if (o.getClass() != getClass())
            return false;

        Action action = (Action) o;

        return this.getDisplayName().equalsIgnoreCase(action.getDisplayName());
    }

    public List<String> urlsArray() {
        List<String> urls = Arrays.asList(getUrl().replace(" ", "").replace("[", "").replace("]", "").split(","));

        return urls;
    }

    public List<String> requestTypesArray() {
        List<String> urls = Arrays.asList(getRequestType().replace(" ", "").replace("[", "").replace("]", "").split(","));

        return urls;
    }

}
