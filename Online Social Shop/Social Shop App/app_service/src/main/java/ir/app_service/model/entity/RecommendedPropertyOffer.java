package ir.app_service.model.entity;

import ir.app_service.model.entity.enumeration.RecommendedPropertyOfferType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table
@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class RecommendedPropertyOffer extends BaseEntity {

    @ManyToOne
    private PropertyFileFindRequest propertyFileFindRequest;

    @ManyToOne
    private PropertyFileOffer propertyFileOffer;

    @Enumerated(EnumType.STRING)
    private RecommendedPropertyOfferType recommendedPropertyOfferType;

}
