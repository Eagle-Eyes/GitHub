package ir.app_service.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table
@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Payment extends BaseEntity {

    @Column
    private String attributes;

    @Column
    private Date payedDate;

}
