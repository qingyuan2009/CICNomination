package sap.com.nomination.repository;

import org.springframework.data.repository.CrudRepository;
import sap.com.nomination.pojo.Role;
import java.util.UUID;

public interface RoleRepository extends CrudRepository<Role, UUID> {
}
