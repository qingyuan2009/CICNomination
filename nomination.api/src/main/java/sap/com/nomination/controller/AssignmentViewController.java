package sap.com.nomination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sap.com.nomination.exception.EntityNotFoundException;
import sap.com.nomination.service.AssignmentViewService;
import sap.com.nomination.view.AssignmentView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AssignmentViewController {

    @Autowired
    AssignmentViewService assignmentViewService;

    @RequestMapping(value = "/assignmentview", method = RequestMethod.GET)
    public ResponseEntity<Iterable<AssignmentView>> findAll(
            @RequestParam Map<String, String> allRequestParams,
            HttpServletRequest request) {
        return new ResponseEntity<>(assignmentViewService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/assignmentview/{id}")
    public ResponseEntity<AssignmentView> getEntityById(
            @PathVariable("id") UUID uuid) {
        try {
            Optional<AssignmentView> assignmentviewView = assignmentViewService.findById(uuid);
            return new ResponseEntity<>(assignmentviewView.get(), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/assignmentview")
    public ResponseEntity<AssignmentView> create(@RequestBody AssignmentView assignmentviewView) {
        try {
            return new ResponseEntity<>(assignmentViewService.create(assignmentviewView), HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/assignmentview/{id}")
    public ResponseEntity<AssignmentView> update(@PathVariable("id") UUID uuid, @RequestBody AssignmentView assignmentviewView) {
        try {
            assignmentviewView.setId(uuid);
            return new ResponseEntity<>(assignmentViewService.update(assignmentviewView), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/assignmentview/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") UUID uuid) {
        try {
            assignmentViewService.deleteById(uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
