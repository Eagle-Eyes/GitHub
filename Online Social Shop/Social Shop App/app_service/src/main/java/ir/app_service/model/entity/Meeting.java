package ir.app_service.model.entity;

import ir.app_service.model.entity.enumeration.MeetingType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Set;

@Table
@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Meeting extends BaseEntity {

    @OneToMany(mappedBy = "meeting")
    private Set<Attendee> attendees;

    @Column
    private Date date;

    @Column
    private Time startTime;

    @Column
    private Time endTime;

    @ManyToOne
    private Location location;

    @Enumerated(EnumType.STRING)
    private MeetingType meetingType;


}
