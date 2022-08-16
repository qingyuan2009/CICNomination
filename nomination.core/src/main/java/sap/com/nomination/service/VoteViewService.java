package sap.com.nomination.service;

import sap.com.nomination.view.VoteView;

import java.util.Optional;
import java.util.UUID;

public interface VoteViewService {

    Iterable<VoteView> findAll();

    Optional<VoteView> findById(UUID uuid);

    VoteView create(VoteView voteView);

    VoteView update(VoteView voteView);

    void deleteById(UUID uuid);
}
