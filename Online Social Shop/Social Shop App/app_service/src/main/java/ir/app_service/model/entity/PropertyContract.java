package ir.app_service.model.entity;

import ir.app_service.model.entity.enumeration.CommissionType;
import ir.app_service.model.entity.enumeration.ContractType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table
@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class PropertyContract extends BaseEntity {

    @Column
    private String trackingCode;

    @OneToMany
    private Set<Payment> payments;

    @Column
    private Date dateOfContract;

    @Column
    private boolean archived;

    @ManyToOne
    private Location contractRoom;

    @OneToOne
    private PropertyFileOffer propertyFileOffer;

    @OneToOne
    private PropertyFileFindRequest propertyFileFindRequest;

    @Enumerated(EnumType.STRING)
    private CommissionType commissionType;

    @Enumerated(EnumType.STRING)
    private ContractType contractType;

    @ManyToOne
    private Property property;


}
