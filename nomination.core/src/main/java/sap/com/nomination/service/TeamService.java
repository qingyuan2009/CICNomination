package sap.com.nomination.service;

import sap.com.nomination.pojo.Team;
import java.util.Optional;
import java.util.UUID;

public interface TeamService {

    Iterable<Team> findAll();

    Optional<Team> findById(UUID uuid);

    Team create(Team team);

    Team update(Team team);

    void deleteById(UUID uuid);
}
