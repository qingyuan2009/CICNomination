package sap.com.nomination.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sap.com.nomination.exception.EntityNotFoundException;
import sap.com.nomination.pojo.Nomination;
import sap.com.nomination.pojo.User;
import sap.com.nomination.pojo.Vote;
import sap.com.nomination.repository.NominationRepository;
import sap.com.nomination.repository.UserRepository;
import sap.com.nomination.repository.VoteRepository;
import sap.com.nomination.service.VoteViewService;
import sap.com.nomination.view.VoteView;

import java.util.*;

@Service
public class VoteViewServiceImpl implements VoteViewService {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    NominationRepository nominationRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<VoteView> findAll() {
        Iterable<Vote> votes = voteRepository.findAll();
        List<VoteView> votesView = new ArrayList<>();
        for (Vote vote: votes) {
            VoteView vv = new VoteView();
            vv.setId(vote.getId());           
            vv.setVersion(vote.getVersion());
            vv.setCreatedBy(vote.getCreatedBy());
            vv.setCreatedDate(vote.getCreatedDate());
            vv.setModifiedBy(vote.getModifiedBy());
            vv.setModifiedDate(vote.getModifiedDate());
            if (!vote.getNominationId().equals("") ){
                Optional<Nomination> nomination = nominationRepository.findById(vote.getNominationId());
                vv.setNomination(nomination.get());
            }
            if (!vote.getVotedBy().equals("") ){
                Optional<User> votedBy = userRepository.findById(vote.getVotedBy());
                vv.setVotedBy(votedBy.get());
            }
            votesView.add(vv);
        }
        return votesView;
    }

    @Override
    public Optional<VoteView> findById(UUID uuid) throws EntityNotFoundException {

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
        Vote vote = voteData.get();
        VoteView vv = new VoteView();
        vv.setId(vote.getId());        
        vv.setVersion(vote.getVersion());
        vv.setCreatedBy(vote.getCreatedBy());
        vv.setCreatedDate(vote.getCreatedDate());
        vv.setModifiedBy(vote.getModifiedBy());
        vv.setModifiedDate(vote.getModifiedDate());
        vv.setNomination(nominationData.get());
        vv.setVotedBy(votedByData.get());
        return Optional.of(vv);
    }


    @Override
    public VoteView create(VoteView voteView) throws EntityNotFoundException{
        Optional<Nomination> nomination = nominationRepository.findById(voteView.getNomination().getId());
        if (!nomination.isPresent()){
            throw new EntityNotFoundException(voteView.getNomination().getId(), "Nomination");
        }
        Optional<User> votedBy = userRepository.findById(voteView.getVotedBy().getId());
        if (!votedBy.isPresent()){
            throw new EntityNotFoundException(voteView.getVotedBy().getId(), "VotedBy");
        }
        Vote _vote = new Vote();        
        //_vote.setCreatedBy(voteView.getCreatedBy());
        _vote.setCreatedDate(new Date());
        _vote.setNominationId(voteView.getNomination().getId());
        _vote.setVotedBy(voteView.getVotedBy().getId());
        Vote result = voteRepository.save(_vote);
        return findById(result.getId()).get();
    }

    @Override
    @Transactional
    public VoteView update(VoteView voteView) throws EntityNotFoundException {
        Optional<Nomination> nomination = nominationRepository.findById(voteView.getNomination().getId());
        if (!nomination.isPresent()){
            throw new EntityNotFoundException(voteView.getNomination().getId(), "Nomination");
        }
        Optional<User> votedBy = userRepository.findById(voteView.getVotedBy().getId());
        if (!votedBy.isPresent()){
            throw new EntityNotFoundException(voteView.getVotedBy().getId(), "VotedBy");
        }
        Optional<Vote> vote = voteRepository.findById(voteView.getId());
        if (!vote.isPresent()){
            throw new EntityNotFoundException(voteView.getId(), "Vote");
        }
        Vote _vote = new Vote();
        _vote.setId(voteView.getId());        
        _vote.setVersion(voteView.getVersion());
        //_vote.setModifiedBy(voteView.getModifiedBy());
        _vote.setModifiedDate(new Date());
        _vote.setNominationId(voteView.getNomination().getId());
        _vote.setVotedBy(voteView.getVotedBy().getId());
        Vote result = voteRepository.save(_vote);
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
