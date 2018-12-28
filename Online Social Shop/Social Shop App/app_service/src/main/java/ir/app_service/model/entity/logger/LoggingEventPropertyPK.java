package ir.app_service.model.entity.logger;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class LoggingEventPropertyPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private LoggingEvent eventId;

    @Column(name = "mapped_key", nullable = false, length = 254)
    private String mappedKey;

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final LoggingEventPropertyPK that = (LoggingEventPropertyPK) o;
        return eventId == that.eventId &&
                Objects.equals(mappedKey, that.mappedKey);
    }

    @Override
    public int hashCode() {

        return Objects.hash(eventId, mappedKey);
    }
}
