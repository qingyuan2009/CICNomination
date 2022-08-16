package sap.com.nomination.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import sap.com.nomination.pojo.Nomination;
import sap.com.nomination.pojo.User;

import java.util.Date;
import java.util.UUID;

@Data
public class VoteView {

    private UUID id;

    private Nomination nomination;

    private User votedBy;

    /**
     * Optimistic Lock
     */
    private int version;

    /**
     * auditor
     */
    @JsonIgnore
    private String createdBy;

    @JsonIgnore
    private String modifiedBy;

    @JsonIgnore
    private Date createdDate;

    @JsonIgnore
    private Date modifiedDate;
}
