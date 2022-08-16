package sap.com.nomination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sap.com.nomination.exception.EntityNotFoundException;
import sap.com.nomination.service.VoteViewService;
import sap.com.nomination.view.VoteView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class VoteViewController {

    @Autowired
    VoteViewService voteViewService;

    @RequestMapping(value = "/voteview", method = RequestMethod.GET)
    public ResponseEntity<Iterable<VoteView>> findAll(
            @RequestParam Map<String, String> allRequestParams,
            HttpServletRequest request) {
            return new ResponseEntity<>(voteViewService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/voteview/{id}")
    public ResponseEntity<VoteView> getEntityById(
            @PathVariable("id") UUID uuid) {
        try {
            Optional<VoteView> voteView = voteViewService.findById(uuid);
            return new ResponseEntity<>(voteView.get(), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/voteview")
    public ResponseEntity<VoteView> create(@RequestBody VoteView voteView) {
        try{
            return new ResponseEntity<>(voteViewService.create(voteView), HttpStatus.CREATED);
        } catch (EntityNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/voteview/{id}")
    public ResponseEntity<VoteView> update(@PathVariable("id") UUID uuid, @RequestBody VoteView voteView) {
        try{
            voteView.setId(uuid);
            return new ResponseEntity<>(voteViewService.update(voteView), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/voteview/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") UUID uuid) {
        try {
            voteViewService.deleteById(uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }



}
