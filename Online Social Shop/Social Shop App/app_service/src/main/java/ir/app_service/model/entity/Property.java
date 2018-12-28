package ir.app_service.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Table
@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Property extends BaseEntity {


    // JSON
    private String attributes;

    @OneToMany
    private Set<Account> owners;

    @OneToMany
    private Set<Document> documents;

    @Column
    private Location location;

    @OneToMany
    private Set<PropertyFileOffer> propertyFileOffers;

    @OneToMany(mappedBy = "property")
    private Set<PropertyContract> propertyContracts;
}
