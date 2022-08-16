package sap.com.nomination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sap.com.nomination.exception.EntityNotFoundException;
import sap.com.nomination.service.UserViewService;
import sap.com.nomination.view.UserView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/api")
public class UserViewController {

    @Autowired
    UserViewService userViewService;

    @RequestMapping(value = "/userview", method = RequestMethod.GET)
    public ResponseEntity<Iterable<UserView>> findAll(
            @RequestParam Map<String, String> allRequestParams,
            HttpServletRequest request) {
            return new ResponseEntity<>(userViewService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/userview/{id}")
    public ResponseEntity<UserView> getEntityById(
            @PathVariable("id") UUID uuid) {
        try {
            Optional<UserView> userView = userViewService.findById(uuid);
            return new ResponseEntity<>(userView.get(), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/userview")
    public ResponseEntity<UserView> create(@RequestBody UserView userView) {
        try{
            return new ResponseEntity<>(userViewService.create(userView), HttpStatus.CREATED);
        } catch (EntityNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/userview/{id}")
    public ResponseEntity<UserView> update(@PathVariable("id") UUID uuid, @RequestBody UserView userView) {
        try{
            userView.setId(uuid);
            return new ResponseEntity<>(userViewService.update(userView), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/userview/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") UUID uuid) {
        try {
            userViewService.deleteById(uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
