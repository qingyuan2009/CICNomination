package sap.com.nomination.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sap.com.nomination.exception.EntityNotFoundException;
import sap.com.nomination.pojo.Role;
import sap.com.nomination.repository.RoleRepository;
import sap.com.nomination.service.RoleService;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(UUID uuid) throws EntityNotFoundException {
        Optional<Role> role = roleRepository.findById(uuid);
        if (!role.isPresent()){
            throw new EntityNotFoundException(uuid, "Role");
        }
        return role;
    }

    @Override
    public Role create(Role role) {
        role.setId(UUID.randomUUID());
        role.setCreatedDate(new Date());
        //_role.setCreatedBy("");
        return  roleRepository.save(role);
    }

    @Override
    public Role update(Role role) throws EntityNotFoundException {
        Optional<Role> roleData = roleRepository.findById(role.getId());
        if (!roleData.isPresent()){
            throw new EntityNotFoundException(role.getId(), "Role");
        }
        role.setModifiedDate(new Date());
        //role.setModifiedBy("");
        return  roleRepository.save(role);
    }

    @Override
    public void deleteById(UUID uuid) throws EntityNotFoundException{
        Optional<Role> roleData = roleRepository.findById(uuid);
        if (!roleData.isPresent()){
            throw new EntityNotFoundException(uuid, "Role");
        }
        roleRepository.deleteById(uuid);
    }

}
