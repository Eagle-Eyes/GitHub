package ir.app_service.model.entity.logger;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "LOGGING_EVENT_PROPERTY")
public class LoggingEventProperty {

    @EmbeddedId
    private LoggingEventPropertyPK loggingEventPropertyPK;

    @Column(name = "mapped_value", nullable = true, columnDefinition = "TEXT")
    private String mappedValue;
}
