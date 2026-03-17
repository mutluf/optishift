package com.example.optishift.controller;

import com.example.optishift.dto.ShiftCreateRequest;
import com.example.optishift.dto.ShiftResponse;
import com.example.optishift.dto.ShiftUpdateRequest;
import com.example.optishift.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shifts")
@RequiredArgsConstructor
public class ShiftController {

    private final ShiftService shiftService;

    @PostMapping
    public ResponseEntity<ShiftResponse> createShift(@RequestBody ShiftCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(shiftService.createShift(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShiftResponse> getShiftById(@PathVariable Long id) {
        return ResponseEntity.ok(shiftService.getShiftById(id));
    }

    @GetMapping
    public ResponseEntity<List<ShiftResponse>> getAllShifts() {
        return ResponseEntity.ok(shiftService.getAllShifts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShiftResponse> updateShift(
            @PathVariable Long id,
            @RequestBody ShiftUpdateRequest request) {
        return ResponseEntity.ok(shiftService.updateShift(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShift(@PathVariable Long id) {
        shiftService.deleteShift(id);
        return ResponseEntity.noContent().build();
    }
}