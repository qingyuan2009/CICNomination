package sap.com.nomination.service;

import sap.com.nomination.pojo.Assignment;

import java.util.Optional;
import java.util.UUID;

public interface AssignmentService {

    Iterable<Assignment> findAll();

    Optional<Assignment> findById(UUID uuid);

    Assignment create(Assignment assignment);

    Assignment update(Assignment assignment);

    void deleteById(UUID uuid);
}
