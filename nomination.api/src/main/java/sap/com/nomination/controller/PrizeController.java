package sap.com.nomination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sap.com.nomination.exception.EntityNotFoundException;
import sap.com.nomination.pojo.Prize;
import sap.com.nomination.service.PrizeService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PrizeController {

    @Autowired
    PrizeService prizeService;

    @RequestMapping(value = "/prize", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Prize>> findAll(
            @RequestParam Map<String, String> allRequestParams,
            HttpServletRequest request) {
            return new ResponseEntity<>(prizeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/prize/{id}")
    public ResponseEntity<Prize> getEntityById(
            @PathVariable("id") UUID uuid) {
        try {
            Optional<Prize> prize = prizeService.findById(uuid);
            return new ResponseEntity<>(prize.get(), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/prize")
    public ResponseEntity<Prize> create(@RequestBody Prize prize) {
            return new ResponseEntity<>(prizeService.create(prize), HttpStatus.CREATED);
    }

    @PutMapping("/prize/{id}")
    public ResponseEntity<Prize> update(@PathVariable("id") UUID uuid, @RequestBody Prize prize) {
        try{
            Optional<Prize> prizeData = prizeService.findById(uuid);
            prize.setId(uuid);
            return new ResponseEntity<>(prizeService.update(prize), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/prize/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") UUID uuid) {
        try {
            prizeService.deleteById(uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
