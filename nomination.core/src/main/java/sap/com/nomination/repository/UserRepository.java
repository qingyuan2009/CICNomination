package sap.com.nomination.repository;

import org.springframework.data.repository.CrudRepository;
import sap.com.nomination.pojo.User;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
}
