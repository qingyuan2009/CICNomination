package sap.com.nomination.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "votes")
@NoArgsConstructor
public class Vote implements Serializable {

    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id = UUID.randomUUID();

    @Column(name = "nomination_id")
    private UUID nominationId;

    @Column(name = "voted_by")
    private UUID votedBy;

    /**
     * Optimistic Lock
     */
    @Version
    private int version;

    /**
     * auditor
     */
    @JsonIgnore
    @CreatedBy
    private String createdBy;

    @JsonIgnore
    @LastModifiedBy
    private String modifiedBy;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdDate = new Date();

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date modifiedDate = new Date();

}
