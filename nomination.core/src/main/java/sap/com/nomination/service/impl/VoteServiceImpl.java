package sap.com.nomination.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sap.com.nomination.exception.EntityNotFoundException;
import sap.com.nomination.pojo.Vote;
import sap.com.nomination.pojo.Nomination;
import sap.com.nomination.pojo.User;
import sap.com.nomination.repository.VoteRepository;
import sap.com.nomination.repository.NominationRepository;
import sap.com.nomination.repository.UserRepository;
import sap.com.nomination.service.VoteService;

import java.util.*;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    NominationRepository nominationRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<Vote> findAll() {
        return voteRepository.findAll();
    }

    @Override
    public Optional<Vote> findById(UUID uuid) throws EntityNotFoundException {

        Optional<Vote> voteData = voteRepository.findById(uuid);
        if (!voteData.isPresent()){
            throw new EntityNotFoundException(uuid, "Vote");
        }
        Optional<Nomination> nominationData = nominationRepository.findById(voteData.get().getNominationId());
        if (!nominationData.isPresent()){
            throw new EntityNotFoundException(voteData.get().getNominationId(), "Nomination");
        }
        Optional<User> votedByData = userRepository.findById(voteData.get().getVotedBy());
        if (!votedByData.isPresent()){
            throw new EntityNotFoundException(voteData.get().getVotedBy(), "VotedBy");
        }
        return voteData;
    }

    @Override
    public Vote create(Vote vote) throws EntityNotFoundException{
        Optional<Nomination> nomination = nominationRepository.findById(vote.getNominationId());
        if (!nomination.isPresent()){
            throw new EntityNotFoundException(vote.getNominationId(), "Nomination");
        }
        Optional<User> votedBy = userRepository.findById(vote.getVotedBy());
        if (!votedBy.isPresent()){
            throw new EntityNotFoundException(vote.getVotedBy(), "VotedBy");
        }
        vote.setId(UUID.randomUUID());
        //_vote.setCreatedBy(vote.getCreatedBy());
        vote.setCreatedDate(new Date());
        Vote result = voteRepository.save(vote);
        return findById(result.getId()).get();
    }

    @Override
    @Transactional
    public Vote update(Vote vote) throws EntityNotFoundException {
        Optional<Nomination> nomination = nominationRepository.findById(vote.getNominationId());
        if (!nomination.isPresent()){
            throw new EntityNotFoundException(vote.getNominationId(), "Nomination");
        }
        Optional<User> votedBy = userRepository.findById(vote.getVotedBy());
        if (!votedBy.isPresent()){
            throw new EntityNotFoundException(vote.getVotedBy(), "VotedBy");
        }
        Optional<Vote> voteData = voteRepository.findById(vote.getId());
        if (!voteData.isPresent()){
            throw new EntityNotFoundException(vote.getId(), "Vote");
        }
        //vote.setModifiedBy(vote.getModifiedBy());
        vote.setModifiedDate(new Date());
        Vote result = voteRepository.save(vote);
        return findById(result.getId()).get();
    }

    @Override
    public void deleteById(UUID uuid) {
        Optional<Vote> voteData = voteRepository.findById(uuid);
        if (!voteData.isPresent()){
            throw new EntityNotFoundException(uuid, "Vote");
        }
        voteRepository.deleteById(uuid);
    }

}
