package sap.com.nomination.repository;

import org.springframework.data.repository.CrudRepository;
import sap.com.nomination.pojo.Team;
import java.util.UUID;

public interface TeamRepository extends CrudRepository<Team, UUID> {
}
