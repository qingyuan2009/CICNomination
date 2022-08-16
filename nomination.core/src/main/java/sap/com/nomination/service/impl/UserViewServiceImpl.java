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
import sap.com.nomination.service.UserViewService;
import sap.com.nomination.view.UserView;
import java.util.*;

@Service
public class UserViewServiceImpl implements UserViewService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public Iterable<UserView> findAll() {
        Iterable<User> users = userRepository.findAll();
        List<UserView> usersView = new ArrayList<>();
        for (User user: users) {
            UserView uv = new UserView();
            uv.setId(user.getId());
            uv.setUserId(user.getUserId());
            uv.setFirstName(user.getFirstName());
            uv.setLastName(user.getLastName());
            uv.setLoginName(user.getLoginName());
            uv.setPassword(user.getPassword());
            uv.setVersion(user.getVersion());
            uv.setCreatedBy(user.getCreatedBy());
            uv.setCreatedDate(user.getCreatedDate());
            uv.setModifiedBy(user.getModifiedBy());
            uv.setModifiedDate(user.getModifiedDate());
            if (!user.getRoleId().equals("") ){
                Optional<Role> role = roleRepository.findById(user.getRoleId());
                uv.setRole(role.get());
            }
            if (!user.getTeamId().equals("") ){
                Optional<Team> team = teamRepository.findById(user.getTeamId());
                uv.setTeam(team.get());
            }
            usersView.add(uv);
        }
        return usersView;
    }

    @Override
    public Optional<UserView> findById(UUID uuid) throws EntityNotFoundException {

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
        User user = userData.get();
        UserView uv = new UserView();
        uv.setId(user.getId());
        uv.setUserId(user.getUserId());
        uv.setFirstName(user.getFirstName());
        uv.setLastName(user.getLastName());
        uv.setLoginName(user.getLoginName());
        uv.setPassword(user.getPassword());
        uv.setVersion(user.getVersion());
        uv.setCreatedBy(user.getCreatedBy());
        uv.setCreatedDate(user.getCreatedDate());
        uv.setModifiedBy(user.getModifiedBy());
        uv.setModifiedDate(user.getModifiedDate());
        uv.setRole(roleData.get());
        uv.setTeam(teamData.get());
        return Optional.of(uv);
    }

    @Override
    public UserView create(UserView userView) throws EntityNotFoundException{
        Optional<Role> role = roleRepository.findById(userView.getRole().getId());
        if (!role.isPresent()){
            throw new EntityNotFoundException(userView.getRole().getId(), "Role");
        }
        Optional<Team> team = teamRepository.findById(userView.getTeam().getId());
        if (!team.isPresent()){
            throw new EntityNotFoundException(userView.getTeam().getId(), "Team");
        }
        User _user = new User();
        _user.setUserId(userView.getUserId());
        _user.setFirstName(userView.getFirstName());
        _user.setLastName(userView.getLastName());
        _user.setLoginName(userView.getLoginName());
        _user.setPassword(userView.getPassword());
        //_user.setCreatedBy(userView.getCreatedBy());
        _user.setCreatedDate(new Date());
        _user.setRoleId(userView.getRole().getId());
        _user.setTeamId(userView.getTeam().getId());
        User result = userRepository.save(_user);
        return findById(result.getId()).get();
    }

    @Override
    @Transactional
    public UserView update(UserView userView) throws EntityNotFoundException {
        Optional<Role> role = roleRepository.findById(userView.getRole().getId());
        if (!role.isPresent()){
            throw new EntityNotFoundException(userView.getRole().getId(), "Role");
        }
        Optional<Team> team = teamRepository.findById(userView.getTeam().getId());
        if (!team.isPresent()){
            throw new EntityNotFoundException(userView.getTeam().getId(), "Team");
        }
        Optional<User> user = userRepository.findById(userView.getId());
        if (!user.isPresent()){
            throw new EntityNotFoundException(userView.getId(), "User");
        }
        User _user = new User();
        _user.setId(userView.getId());
        _user.setUserId(userView.getUserId());
        _user.setFirstName(userView.getFirstName());
        _user.setLastName(userView.getLastName());
        _user.setLoginName(userView.getLoginName());
        _user.setPassword(userView.getPassword());
        _user.setVersion(userView.getVersion());
        //_user.setModifiedBy(userView.getModifiedBy());
        _user.setModifiedDate(new Date());
        _user.setRoleId(userView.getRole().getId());
        _user.setTeamId(userView.getTeam().getId());
        User result = userRepository.save(_user);
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
