package sap.com.nomination.service;

import sap.com.nomination.view.AssignmentView;

import java.util.Optional;
import java.util.UUID;

public interface AssignmentViewService {

    Iterable<AssignmentView> findAll();

    Optional<AssignmentView> findById(UUID uuid);

    AssignmentView create(AssignmentView assignmentView);

    AssignmentView update(AssignmentView assignmentView);

    void deleteById(UUID uuid);
}
