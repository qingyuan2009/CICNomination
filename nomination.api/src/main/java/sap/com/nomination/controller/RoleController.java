package sap.com.nomination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sap.com.nomination.exception.EntityNotFoundException;
import sap.com.nomination.pojo.Role;
import sap.com.nomination.service.RoleService;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Role>> findAll(
            @RequestParam Map<String, String> allRequestParams,
            HttpServletRequest request) {
            return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<Role> getEntityById(
            @PathVariable("id") UUID uuid) {
        try {
            Optional<Role> role = roleService.findById(uuid);
            return new ResponseEntity<>(role.get(), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/role")
    public ResponseEntity<Role> create(@RequestBody Role role) {
            return new ResponseEntity<>(roleService.create(role), HttpStatus.CREATED);
    }

    @PutMapping("/role/{id}")
    public ResponseEntity<Role> update(@PathVariable("id") UUID uuid, @RequestBody Role role) {
        try{
            Optional<Role> roleData = roleService.findById(uuid);
            role.setId(uuid);
            return new ResponseEntity<>(roleService.update(role), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/role/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") UUID uuid) {
        try {
            roleService.deleteById(uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
