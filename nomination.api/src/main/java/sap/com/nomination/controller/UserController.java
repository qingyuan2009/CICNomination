package sap.com.nomination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sap.com.nomination.exception.EntityNotFoundException;
import sap.com.nomination.pojo.User;
import sap.com.nomination.service.UserService;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<Iterable<User>> findAll(
            @RequestParam Map<String, String> allRequestParams,
            HttpServletRequest request) {
            return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getEntityById(
            @PathVariable("id") UUID uuid) {
        try {
            Optional<User> userView = userService.findById(uuid);
            return new ResponseEntity<>(userView.get(), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<User> create(@RequestBody User userView) {
        try{
            return new ResponseEntity<>(userService.create(userView), HttpStatus.CREATED);
        } catch (EntityNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> update(@PathVariable("id") UUID uuid, @RequestBody User userView) {
        try{
            userView.setId(uuid);
            return new ResponseEntity<>(userService.update(userView), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") UUID uuid) {
        try {
            userService.deleteById(uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
