package com.suburb.app.controller;

import com.suburb.app.domain.Suburb;
import com.suburb.app.service.SuburbService;
import com.suburb.app.wrapper.PostalCodeRangeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/suburb")
public class SuburbController {

    @Autowired
    SuburbService suburbService;

    public SuburbController(SuburbService suburbService) {
        this.suburbService = suburbService;
    }

    @PostMapping
    public ResponseEntity<List<Suburb>> save(@RequestBody List<Suburb> suburbs){
        return ResponseEntity.ok(suburbService.save(suburbs));
    }


    @GetMapping("/{start}/{end}")
    public ResponseEntity<PostalCodeRangeResponse> find(@PathVariable Long start, @PathVariable Long end){
        return ResponseEntity.ok(suburbService.findSuburbsBetweenPostalCodes(start,end));
    }
}
