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
@Table(name = "periods")
@NoArgsConstructor
public class Period implements Serializable {

    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id = UUID.randomUUID();

    @Column(name = "period_name")
    private String periodName;

    @Column(name = "normination_start")
    private Date norminationStart;

    @Column(name = "normination_end")
    private Date norminationEnd;

    @Column(name = "vote_start")
    private Date voteStart;

    @Column(name = "vote_end")
    private Date voteEnd;

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
