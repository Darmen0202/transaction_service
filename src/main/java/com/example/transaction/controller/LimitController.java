package com.example.transaction.controller;

import com.example.transaction.dto.LimitDTO;
import com.example.transaction.service.LimitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST-контроллер для управления лимитами.
 */
@RestController
@RequestMapping("/api/limits")
public class LimitController {

    @Autowired
    private LimitService limitService;

    /**
     * Обновляет лимит.
     *
     * @param limitDTO — объект ограничения передачи данных.
     * @вернуть обновленный лимит.
     */
    @PostMapping
    public ResponseEntity<LimitDTO> updateLimit(@RequestBody LimitDTO limitDTO) {
        LimitDTO savedLimit = limitService.saveLimit(limitDTO);
        return ResponseEntity.ok(savedLimit);
    }
}
