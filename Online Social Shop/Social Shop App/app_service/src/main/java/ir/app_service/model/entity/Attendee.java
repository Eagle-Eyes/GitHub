package ir.app_service.model.entity;

import ir.app_service.model.entity.enumeration.AttendeeType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table
@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Attendee extends BaseEntity {

    @ManyToOne
    private Account attendee;

    @Enumerated(EnumType.STRING)
    private AttendeeType attendeeType;

    @ManyToOne
    private Meeting meeting;

}
