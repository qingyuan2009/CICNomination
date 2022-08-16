package sap.com.nomination.service;

import sap.com.nomination.pojo.Vote;
import sap.com.nomination.view.VoteView;

import java.util.Optional;
import java.util.UUID;

public interface VoteService {

    Iterable<Vote> findAll();

    Optional<Vote> findById(UUID uuid);

    Vote create(Vote vote);

    Vote update(Vote vote);

    void deleteById(UUID uuid);
}
