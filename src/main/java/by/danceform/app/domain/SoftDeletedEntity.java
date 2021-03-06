package by.danceform.app.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class SoftDeletedEntity<ID extends Serializable> extends AbstractAuditingEntity<ID> {

    private static final long serialVersionUID = -335144964285137631L;

    @Column(nullable = false)
    private boolean deleted = false;

    public boolean getDeleted() {
        return this.deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
