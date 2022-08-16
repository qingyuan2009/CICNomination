package sap.com.nomination.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sap.com.nomination.exception.EntityNotFoundException;
import sap.com.nomination.pojo.*;
import sap.com.nomination.repository.*;
import sap.com.nomination.service.DataImportService;
import sap.com.nomination.service.PeriodService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DataImportServiceImpl implements DataImportService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PeriodRepository periodRepository;

    @Autowired
    PrizeRepository prizeRepository;

    @Autowired
    AssignmentRepository assignmentRepository;

    @Autowired
    NominationRepository nominationRepository;

    @Autowired
    VoteRepository voteRepository;

    @Override
    @Transactional
    public void dataImport(Bundle bundle) {
        Iterable<Role> roles = bundle.getRoles();
        Iterable<Team> teams = bundle.getTeams();
        Iterable<User> users = bundle.getUsers();
        Iterable<Period> periods = bundle.getPeriods();
        Iterable<Prize> prizes = bundle.getPrizes();
        Iterable<Assignment> assignments = bundle.getAssignments();
        Iterable<Nomination> nominations = bundle.getNominations();
        Iterable<Vote> votes = bundle.getVotes();
        roleRepository.saveAll(roles);
        teamRepository.saveAll(teams);
        userRepository.saveAll(users);
        periodRepository.saveAll(periods);
        prizeRepository.saveAll(prizes);
        assignmentRepository.saveAll(assignments);
        nominationRepository.saveAll(nominations);
        voteRepository.saveAll(votes);
    }
}
