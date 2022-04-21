package com.mytest.fni.domain;

import com.mytest.fni.domain.enumeration.SyncStatus;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A FniTOFinancierSyncStatus.
 */
@Entity
@Table(name = "fni_to_financier_sync_status")
public class FniTOFinancierSyncStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "sync_date_time_stamp")
    private LocalDate syncDateTimeStamp;

    @Enumerated(EnumType.STRING)
    @Column(name = "sync_status")
    private SyncStatus syncStatus;

    @Column(name = "comments")
    private String comments;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public FniTOFinancierSyncStatus id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getSyncDateTimeStamp() {
        return this.syncDateTimeStamp;
    }

    public FniTOFinancierSyncStatus syncDateTimeStamp(LocalDate syncDateTimeStamp) {
        this.setSyncDateTimeStamp(syncDateTimeStamp);
        return this;
    }

    public void setSyncDateTimeStamp(LocalDate syncDateTimeStamp) {
        this.syncDateTimeStamp = syncDateTimeStamp;
    }

    public SyncStatus getSyncStatus() {
        return this.syncStatus;
    }

    public FniTOFinancierSyncStatus syncStatus(SyncStatus syncStatus) {
        this.setSyncStatus(syncStatus);
        return this;
    }

    public void setSyncStatus(SyncStatus syncStatus) {
        this.syncStatus = syncStatus;
    }

    public String getComments() {
        return this.comments;
    }

    public FniTOFinancierSyncStatus comments(String comments) {
        this.setComments(comments);
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FniTOFinancierSyncStatus)) {
            return false;
        }
        return id != null && id.equals(((FniTOFinancierSyncStatus) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FniTOFinancierSyncStatus{" +
            "id=" + getId() +
            ", syncDateTimeStamp='" + getSyncDateTimeStamp() + "'" +
            ", syncStatus='" + getSyncStatus() + "'" +
            ", comments='" + getComments() + "'" +
            "}";
    }
}
