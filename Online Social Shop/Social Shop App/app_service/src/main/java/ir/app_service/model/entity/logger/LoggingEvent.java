package ir.app_service.model.entity.logger;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "LOGGING_EVENT")
public class LoggingEvent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false)
    private long eventId;
    @Column(name = "timestmp", nullable = false)
    private long timestmp;
    @Column(name = "formatted_message", nullable = false, columnDefinition = "TEXT")
    private String formattedMessage;
    @Column(name = "logger_name", nullable = false, length = 254)
    private String loggerName;
    @Column(name = "level_string", nullable = false, length = 254)
    private String levelString;
    @Column(name = "thread_name", length = 254)
    private String threadName;
    @Column(name = "reference_flag")
    private Short referenceFlag;
    @Column(name = "arg0", length = 254)
    private String arg0;
    @Column(name = "arg1", length = 254)
    private String arg1;
    @Column(name = "arg2", length = 254)
    private String arg2;
    @Column(name = "arg3", length = 254)
    private String arg3;
    @Column(name = "caller_filename", nullable = false, length = 254)
    private String callerFilename;
    @Column(name = "caller_class", nullable = false, length = 254)
    private String callerClass;
    @Column(name = "caller_method", nullable = false, length = 254)
    private String callerMethod;
    @Column(name = "caller_line", nullable = false, length = 4)
    private String callerLine;
}
