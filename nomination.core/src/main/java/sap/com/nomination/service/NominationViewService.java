package sap.com.nomination.service;

import sap.com.nomination.view.NominationView;

import java.util.Optional;
import java.util.UUID;

public interface NominationViewService {

    Iterable<NominationView> findAll();

    Optional<NominationView> findById(UUID uuid);

    NominationView create(NominationView nominationView);

    NominationView update(NominationView nominationView);

    void deleteById(UUID uuid);
}
