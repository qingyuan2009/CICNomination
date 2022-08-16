package sap.com.nomination.repository;

import org.springframework.data.repository.CrudRepository;
import sap.com.nomination.pojo.Nomination;
import sap.com.nomination.pojo.User;

import java.util.UUID;

public interface NominationRepository extends CrudRepository<Nomination, UUID> {
}
