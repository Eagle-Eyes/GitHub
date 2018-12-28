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
@Table(name = "LOGGING_EVENT_EXCEPTION")
public class LoggingEventException {

    @EmbeddedId
    private LoggingEventExceptionPK loggingEventExceptionPK;

    @Column(name = "trace_line", nullable = false, length = 254)
    private String traceLine;
}
