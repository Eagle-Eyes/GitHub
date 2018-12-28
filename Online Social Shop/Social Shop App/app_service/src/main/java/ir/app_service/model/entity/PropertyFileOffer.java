package ir.app_service.model.entity;

import ir.app_service.model.entity.enumeration.FileType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Table
@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class PropertyFileOffer extends BaseEntity {

    @ManyToOne
    private Account adviser;

    @ManyToOne
    private Account registrar;

    @ManyToOne
    private Property property;

    @Enumerated(EnumType.STRING)
    private FileType fileType;

    @OneToMany(mappedBy = "propertyFileOffer")
    private Set<RecommendedPropertyOffer> recommendedPropertyOffers;

    @OneToOne(mappedBy = "propertyFileOffer")
    private PropertyContract propertyContract;

    @ManyToMany//(mappedBy = "propertyFileOffers")
    @JoinTable(name = "recommendedPropertyOffer"
            , inverseJoinColumns = {@JoinColumn(name = "property_file_find_request_id")},
            joinColumns = {@JoinColumn(name = "recommended_property_offer_id")}
    )
    private Set<PropertyFileFindRequest> propertyFileFindRequests;

    @Column(length = 9)
    private String code;

    @ManyToMany
    private Set<Meeting> meetings;

}
