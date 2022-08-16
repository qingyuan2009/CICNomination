package sap.com.nomination.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import sap.com.nomination.pojo.Role;
import sap.com.nomination.pojo.Team;

import java.util.Date;
import java.util.UUID;

@Data
public class UserView {

    private UUID id;

    private String userId;

    private String firstName;

    private String lastName;

    private String loginName;

    private String password;

    private Role role;

    private Team team;

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
