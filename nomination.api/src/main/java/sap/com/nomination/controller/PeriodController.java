package sap.com.nomination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sap.com.nomination.exception.EntityNotFoundException;
import sap.com.nomination.pojo.Period;
import sap.com.nomination.service.PeriodService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PeriodController {

    @Autowired
    PeriodService periodService;

    @RequestMapping(value = "/period", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Period>> findAll(
            @RequestParam Map<String, String> allRequestParams,
            HttpServletRequest request) {
            return new ResponseEntity<>(periodService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/period/{id}")
    public ResponseEntity<Period> getEntityById(
            @PathVariable("id") UUID uuid) {
        try {
            Optional<Period> period = periodService.findById(uuid);
            return new ResponseEntity<>(period.get(), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/period")
    public ResponseEntity<Period> create(@RequestBody Period period) {
            return new ResponseEntity<>(periodService.create(period), HttpStatus.CREATED);
    }

    @PutMapping("/period/{id}")
    public ResponseEntity<Period> update(@PathVariable("id") UUID uuid, @RequestBody Period period) {
        try{
            Optional<Period> periodData = periodService.findById(uuid);
            period.setId(uuid);
            return new ResponseEntity<>(periodService.update(period), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/period/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") UUID uuid) {
        try {
            periodService.deleteById(uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
