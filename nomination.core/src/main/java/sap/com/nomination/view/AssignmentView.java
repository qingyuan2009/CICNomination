package sap.com.nomination.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import sap.com.nomination.pojo.Period;
import sap.com.nomination.pojo.Prize;

import java.util.Date;
import java.util.UUID;

@Data
public class AssignmentView {

    private UUID id;

    private Prize prize;

    private Period period;

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
