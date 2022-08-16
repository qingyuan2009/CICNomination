package sap.com.nomination.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sap.com.nomination.exception.EntityNotFoundException;
import sap.com.nomination.pojo.Role;
import sap.com.nomination.pojo.Team;
import sap.com.nomination.pojo.User;
import sap.com.nomination.repository.RoleRepository;
import sap.com.nomination.repository.TeamRepository;
import sap.com.nomination.repository.UserRepository;
import sap.com.nomination.service.UserService;
import sap.com.nomination.service.UserViewService;
import sap.com.nomination.view.UserView;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(UUID uuid) throws EntityNotFoundException {
        Optional<User> userData = userRepository.findById(uuid);
        if (!userData.isPresent()){
            throw new EntityNotFoundException(uuid, "User");
        }
        Optional<Role> roleData = roleRepository.findById(userData.get().getRoleId());
        if (!roleData.isPresent()){
            throw new EntityNotFoundException(userData.get().getRoleId(), "Role");
        }
        Optional<Team> teamData = teamRepository.findById(userData.get().getTeamId());
        if (!teamData.isPresent()){
            throw new EntityNotFoundException(userData.get().getTeamId(), "Team");
        }
        return userData;
    }

    @Override
    public User create(User user) throws EntityNotFoundException{
        Optional<Role> role = roleRepository.findById(user.getRoleId());
        if (!role.isPresent()){
            throw new EntityNotFoundException(user.getRoleId(), "Role");
        }
        Optional<Team> team = teamRepository.findById(user.getTeamId());
        if (!team.isPresent()){
            throw new EntityNotFoundException(user.getTeamId(), "Team");
        }
        user.setId(UUID.randomUUID());  // regenerate UUID
        user.setCreatedDate(new Date());
        User result = userRepository.save(user);
        return findById(result.getId()).get();
    }

    @Override
    @Transactional
    public User update(User user) throws EntityNotFoundException {
        Optional<Role> role = roleRepository.findById(user.getRoleId());
        if (!role.isPresent()){
            throw new EntityNotFoundException(user.getRoleId(), "Role");
        }
        Optional<Team> team = teamRepository.findById(user.getTeamId());
        if (!team.isPresent()){
            throw new EntityNotFoundException(user.getTeamId(), "Team");
        }
        Optional<User> userData = userRepository.findById(user.getId());
        if (!userData.isPresent()){
            throw new EntityNotFoundException(user.getId(), "User");
        }
        user.setModifiedDate(new Date());
        User result = userRepository.save(user);
        return findById(result.getId()).get();
    }

    @Override
    public void deleteById(UUID uuid) {
        Optional<User> userData = userRepository.findById(uuid);
        if (!userData.isPresent()){
            throw new EntityNotFoundException(uuid, "User");
        }
        userRepository.deleteById(uuid);
    }

}
