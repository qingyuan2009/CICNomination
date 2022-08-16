package sap.com.nomination.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Bundle {

    List<Role> roles;

    List<Team> teams;

    List<User> users;

    List<Period> periods;

    List<Prize> prizes;

    List<Assignment> assignments;

    List<Nomination> nominations;

    List<Vote> votes;

}
