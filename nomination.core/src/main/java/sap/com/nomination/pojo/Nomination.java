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
@Table(name = "norminations")
@NoArgsConstructor
public class Nomination implements Serializable {

    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id = UUID.randomUUID();

    @Column(name = "nominee_id")
    private UUID nomineeId;

    @Column(name = "prize_id")
    private UUID prizeId;

    @Column(name = "period_id")
    private UUID periodId;

    @Column(name = "nominator_id")
    private UUID nominatorId;

    @Column(name = "comment")
    private String comment;

    /**
     * Optimistic Lock
     */
    @Version
    private int version;

    /**
     * auditor
     */
    @CreatedBy
    private String createdBy;

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
