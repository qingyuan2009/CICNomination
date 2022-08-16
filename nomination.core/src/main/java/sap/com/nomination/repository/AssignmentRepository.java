package sap.com.nomination.repository;

import org.springframework.data.repository.CrudRepository;
import sap.com.nomination.pojo.Assignment;

import java.util.UUID;

public interface AssignmentRepository extends CrudRepository<Assignment, UUID> {
}
