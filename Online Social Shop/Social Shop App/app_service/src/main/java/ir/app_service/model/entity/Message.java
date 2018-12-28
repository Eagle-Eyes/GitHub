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
public class Message extends BaseEntity {


    @Column
    private String subject;

    @Column
    private String text;

    @ManyToOne
    private Message replyTo;

    @ManyToOne
    private Account sender;

    @ManyToMany(mappedBy = "receivedMessages")
    private Set<Account> accountReceivers;

    @ManyToMany(mappedBy = "receivedMessages")
    private Set<Group> groupReceivers;

    @ManyToMany(mappedBy = "receivedMessages")
    private Set<Role> roleReceivers;

    @ManyToMany
    private Set<Document> attachments;

}
