package sap.com.nomination.service;

import sap.com.nomination.view.UserView;

import java.util.Optional;
import java.util.UUID;

public interface UserViewService {

    Iterable<UserView> findAll();

    Optional<UserView> findById(UUID uuid);

    UserView create(UserView userView);

    UserView update(UserView userView);

    void deleteById(UUID uuid);
}
