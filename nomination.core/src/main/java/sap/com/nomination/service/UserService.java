package sap.com.nomination.service;

import sap.com.nomination.pojo.User;
import sap.com.nomination.view.UserView;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    Iterable<User> findAll();

    Optional<User> findById(UUID uuid);

    User create(User user);

    User update(User user);

    void deleteById(UUID uuid);
}
