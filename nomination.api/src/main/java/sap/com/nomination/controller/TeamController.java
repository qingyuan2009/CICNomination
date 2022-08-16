package sap.com.nomination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sap.com.nomination.exception.EntityNotFoundException;
import sap.com.nomination.pojo.Team;
import sap.com.nomination.service.TeamService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TeamController {

    @Autowired
    TeamService teamService;

    @RequestMapping(value = "/team", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Team>> findAll(
            @RequestParam Map<String, String> allRequestParams,
            HttpServletRequest request) {
            return new ResponseEntity<>(teamService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/team/{id}")
    public ResponseEntity<Team> getEntityById(
            @PathVariable("id") UUID uuid) {
        try {
            Optional<Team> team = teamService.findById(uuid);
            return new ResponseEntity<>(team.get(), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/team")
    public ResponseEntity<Team> create(@RequestBody Team team) {
            return new ResponseEntity<>(teamService.create(team), HttpStatus.CREATED);
    }

    @PutMapping("/team/{id}")
    public ResponseEntity<Team> update(@PathVariable("id") UUID uuid, @RequestBody Team team) {
        try{
            Optional<Team> teamData = teamService.findById(uuid);
            team.setId(uuid);
            return new ResponseEntity<>(teamService.update(team), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/team/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") UUID uuid) {
        try {
            teamService.deleteById(uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
