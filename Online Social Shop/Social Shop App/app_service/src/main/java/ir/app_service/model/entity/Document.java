package ir.app_service.model.entity;

import ir.app_service.model.entity.enumeration.FileType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table
@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Document extends BaseEntity {

    @Column
    private String description;

    @Column
    private Byte[] file;

    @Enumerated(value = EnumType.STRING)
    private FileType fileType;

}
