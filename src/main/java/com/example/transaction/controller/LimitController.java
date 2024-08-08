package com.example.transaction.controller;

import com.example.transaction.dto.LimitDTO;
import com.example.transaction.service.LimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/limits")
public class LimitController {

    @Autowired
    private LimitService limitService;

    @PostMapping
    public ResponseEntity<LimitDTO> updateLimit(@RequestBody LimitDTO limitDTO) {
        LimitDTO savedLimit = limitService.saveLimit(limitDTO);
        return ResponseEntity.ok(savedLimit);
    }
}
