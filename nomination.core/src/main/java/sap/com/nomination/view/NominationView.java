package sap.com.nomination.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import sap.com.nomination.pojo.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class NominationView {

    private UUID id;

    private User nominee;

    private Prize prize;

    private Period period;

    private User nominator;

    private String comment;

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
