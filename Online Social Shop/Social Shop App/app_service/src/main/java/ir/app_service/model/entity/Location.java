package ir.app_service.model.entity;

import ir.app_service.model.entity.enumeration.LocationType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table
@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Location extends BaseEntity {

    @Column
    private String description;

    @Column
    private String latitude;

    @Column
    private String longitude;

    @ManyToOne
    private Location parent;

    @Enumerated(value = EnumType.STRING)
    private LocationType locationType;

}
