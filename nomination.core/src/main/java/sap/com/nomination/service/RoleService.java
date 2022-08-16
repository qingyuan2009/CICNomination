package sap.com.nomination.service;

import sap.com.nomination.pojo.Role;
import java.util.Optional;
import java.util.UUID;

public interface RoleService {

    Iterable<Role> findAll();

    Optional<Role> findById(UUID uuid);

    Role create(Role role);

    Role update(Role role);

    void deleteById(UUID uuid);
}
