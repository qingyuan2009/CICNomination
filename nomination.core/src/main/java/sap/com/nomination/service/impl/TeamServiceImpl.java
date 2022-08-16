package sap.com.nomination.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sap.com.nomination.exception.EntityNotFoundException;
import sap.com.nomination.pojo.Team;
import sap.com.nomination.repository.TeamRepository;
import sap.com.nomination.service.TeamService;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Override
    public Iterable<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public Optional<Team> findById(UUID uuid) throws EntityNotFoundException {
        Optional<Team> team = teamRepository.findById(uuid);
        if (!team.isPresent()){
            throw new EntityNotFoundException(uuid, "Team");
        }
        return team;
    }

    @Override
    public Team create(Team team) {
        team.setId(UUID.randomUUID());
        team.setCreatedDate(new Date());
        //_team.setCreatedBy("");
        return  teamRepository.save(team);
    }

    @Override
    public Team update(Team team) throws EntityNotFoundException {
        Optional<Team> teamData = teamRepository.findById(team.getId());
        if (!teamData.isPresent()){
            throw new EntityNotFoundException(team.getId(), "Team");
        }
        team.setModifiedDate(new Date());
        //team.setModifiedBy("");
        return  teamRepository.save(team);
    }

    @Override
    public void deleteById(UUID uuid) throws EntityNotFoundException{
        Optional<Team> teamData = teamRepository.findById(uuid);
        if (!teamData.isPresent()){
            throw new EntityNotFoundException(uuid, "Team");
        }
        teamRepository.deleteById(uuid);
    }

}
