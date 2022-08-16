package sap.com.nomination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sap.com.nomination.exception.EntityNotFoundException;
import sap.com.nomination.pojo.Vote;
import sap.com.nomination.service.VoteService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class VoteController {

    @Autowired
    VoteService voteService;

    @RequestMapping(value = "/vote", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Vote>> findAll(
            @RequestParam Map<String, String> allRequestParams,
            HttpServletRequest request) {
            return new ResponseEntity<>(voteService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/vote/{id}")
    public ResponseEntity<Vote> getEntityById(
            @PathVariable("id") UUID uuid) {
        try {
            Optional<Vote> vote = voteService.findById(uuid);
            return new ResponseEntity<>(vote.get(), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/vote")
    public ResponseEntity<Vote> create(@RequestBody Vote vote) {
        try{
            return new ResponseEntity<>(voteService.create(vote), HttpStatus.CREATED);
        } catch (EntityNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/vote/{id}")
    public ResponseEntity<Vote> update(@PathVariable("id") UUID uuid, @RequestBody Vote vote) {
        try{
            vote.setId(uuid);
            return new ResponseEntity<>(voteService.update(vote), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/vote/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") UUID uuid) {
        try {
            voteService.deleteById(uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
