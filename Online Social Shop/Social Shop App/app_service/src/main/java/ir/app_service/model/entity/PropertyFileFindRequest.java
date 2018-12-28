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
public class PropertyFileFindRequest extends BaseEntity {


    @ManyToOne
    private Account adviser;

    @ManyToOne
    private Account searcher;

    @OneToOne(mappedBy = "propertyFileFindRequest")
    private PropertyContract propertyContract;


    @OneToMany(mappedBy = "propertyFileFindRequest")
    private Set<RecommendedPropertyOffer> recommendedPropertyOffers;

    @ManyToMany//(mappedBy = "propertyFileFindRequests")
    @JoinTable(
            name = "recommendedPropertyOffer"
            , joinColumns = {@JoinColumn(name = "property_file_find_request_id")},
            inverseJoinColumns = {@JoinColumn(name = "recommended_property_offer_id")}
    )
    private Set<PropertyFileOffer> propertyFileOffers;

    // JSON
    private String matchingCriteria;
}
