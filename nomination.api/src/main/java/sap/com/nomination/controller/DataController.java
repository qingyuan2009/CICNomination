package sap.com.nomination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sap.com.nomination.pojo.Bundle;
import sap.com.nomination.service.DataImportService;

@RestController
@RequestMapping("/api")
public class DataController {

    @Autowired
    DataImportService dataImportService;

    @PostMapping("/data")
    public ResponseEntity<HttpStatus> create(@RequestBody Bundle bundle) {
        dataImportService.dataImport(bundle);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }



}
