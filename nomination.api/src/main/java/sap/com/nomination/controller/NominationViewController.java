package sap.com.nomination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sap.com.nomination.exception.EntityNotFoundException;
import sap.com.nomination.service.NominationViewService;
import sap.com.nomination.view.NominationView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class NominationViewController {

    @Autowired
    NominationViewService nominationViewService;

    @RequestMapping(value = "/nominationview", method = RequestMethod.GET)
    public ResponseEntity<Iterable<NominationView>> findAll(
            @RequestParam Map<String, String> allRequestParams,
            HttpServletRequest request) {
            return new ResponseEntity<>(nominationViewService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/nominationview/{id}")
    public ResponseEntity<NominationView> getEntityById(
            @PathVariable("id") UUID uuid) {
        try {
            Optional<NominationView> nominationView = nominationViewService.findById(uuid);
            return new ResponseEntity<>(nominationView.get(), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/nominationview")
    public ResponseEntity<NominationView> create(@RequestBody NominationView nominationView) {
        try{
            return new ResponseEntity<>(nominationViewService.create(nominationView), HttpStatus.CREATED);
        } catch (EntityNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/nominationview/{id}")
    public ResponseEntity<NominationView> update(@PathVariable("id") UUID uuid, @RequestBody NominationView nominationView) {
        try{
            nominationView.setId(uuid);
            return new ResponseEntity<>(nominationViewService.update(nominationView), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/nominationview/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") UUID uuid) {
        try {
            nominationViewService.deleteById(uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
