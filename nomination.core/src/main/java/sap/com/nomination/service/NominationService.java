package sap.com.nomination.service;

import sap.com.nomination.pojo.Nomination;
import sap.com.nomination.view.NominationView;

import java.util.Optional;
import java.util.UUID;

public interface NominationService {

    Iterable<Nomination> findAll();

    Optional<Nomination> findById(UUID uuid);

    Nomination create(Nomination nomination);

    Nomination update(Nomination nomination);

    void deleteById(UUID uuid);
}
