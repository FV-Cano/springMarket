package com.platzicurso.market.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.platzicurso.market.domain.service.PurchaseService;

import io.swagger.v3.oas.annotations.Operation;

import com.platzicurso.market.domain.Purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @Operation(summary = "Get all purchases")
    @GetMapping("/all")
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get purchases by client id")
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable("clientId") String clientId) {
        return purchaseService.getByClient(clientId)
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @Operation(summary = "Saves a purchase")
    @PostMapping("/save")
    public ResponseEntity<Purchase> save(@RequestBody Purchase entity) {
        return new ResponseEntity<>(purchaseService.save(entity), HttpStatus.CREATED);
    }
    
}
