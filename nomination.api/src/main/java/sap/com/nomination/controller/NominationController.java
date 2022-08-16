package sap.com.nomination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sap.com.nomination.exception.EntityNotFoundException;
import sap.com.nomination.pojo.Nomination;
import sap.com.nomination.service.NominationService;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class NominationController {

    @Autowired
    NominationService nominationService;

    @RequestMapping(value = "/nomination", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Nomination>> findAll(
            @RequestParam Map<String, String> allRequestParams,
            HttpServletRequest request) {
            return new ResponseEntity<>(nominationService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/nomination/{id}")
    public ResponseEntity<Nomination> getEntityById(
            @PathVariable("id") UUID uuid) {
        try {
            Optional<Nomination> nomination = nominationService.findById(uuid);
            return new ResponseEntity<>(nomination.get(), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/nomination")
    public ResponseEntity<Nomination> create(@RequestBody Nomination nomination) {
        try{
            return new ResponseEntity<>(nominationService.create(nomination), HttpStatus.CREATED);
        } catch (EntityNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/nomination/{id}")
    public ResponseEntity<Nomination> update(@PathVariable("id") UUID uuid, @RequestBody Nomination nomination) {
        try{
            nomination.setId(uuid);
            return new ResponseEntity<>(nominationService.update(nomination), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/nomination/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") UUID uuid) {
        try {
            nominationService.deleteById(uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
