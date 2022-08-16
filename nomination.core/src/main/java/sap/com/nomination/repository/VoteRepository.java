package sap.com.nomination.repository;

import org.springframework.data.repository.CrudRepository;
import sap.com.nomination.pojo.Vote;

import java.util.UUID;

public interface VoteRepository extends CrudRepository<Vote, UUID> {
}
