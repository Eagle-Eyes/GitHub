package ir.app_service.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@JsonIgnoreProperties(value = {"registered_date", "update_date", "accesses", "groups"})
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    private Long id;

    @Column
    private boolean active = true;

    @Column(unique = true)
    private String displayName;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;

    @Column
    @CreationTimestamp
    private Date registeredDate;

    @Column
    @UpdateTimestamp
    private Date updatedDate;

    @Override
    public int hashCode() {
        final int PRIME = 31;
        return new HashCodeBuilder((int) (this.id % 2 == 0 ? this.id + 1 : this.id), PRIME).toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (o.getClass() != getClass())
            return false;
        BaseEntity e = (BaseEntity) o;
        return new EqualsBuilder().
                append(this.id, e.id).
                isEquals();
    }
}
