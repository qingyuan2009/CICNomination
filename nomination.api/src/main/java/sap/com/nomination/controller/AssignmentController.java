package sap.com.nomination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sap.com.nomination.exception.EntityNotFoundException;
import sap.com.nomination.pojo.Assignment;
import sap.com.nomination.service.AssignmentService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AssignmentController {

    @Autowired
    AssignmentService assignmentService;

    @RequestMapping(value = "/assignment", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Assignment>> findAll(
            @RequestParam Map<String, String> allRequestParams,
            HttpServletRequest request) {
        return new ResponseEntity<>(assignmentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/assignment/{id}")
    public ResponseEntity<Assignment> getEntityById(
            @PathVariable("id") UUID uuid) {
        try {
            Optional<Assignment> assignmentView = assignmentService.findById(uuid);
            return new ResponseEntity<>(assignmentView.get(), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/assignment")
    public ResponseEntity<Assignment> create(@RequestBody Assignment assignmentView) {
        try {
            return new ResponseEntity<>(assignmentService.create(assignmentView), HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/assignment/{id}")
    public ResponseEntity<Assignment> update(@PathVariable("id") UUID uuid, @RequestBody Assignment assignmentView) {
        try {
            assignmentView.setId(uuid);
            return new ResponseEntity<>(assignmentService.update(assignmentView), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/assignment/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") UUID uuid) {
        try {
            assignmentService.deleteById(uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
