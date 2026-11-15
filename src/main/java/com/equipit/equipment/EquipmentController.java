package com.equipit.equipment;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {
    private final EquipmentService service;

    public EquipmentController(EquipmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Equipment> findAll() {
        return service.findAll();
    }

    @PostMapping("/{id}/borrow")
    public ResponseEntity<Equipment> borrow(@PathVariable Long id, @Valid @RequestBody BorrowRequest request) {
        return ResponseEntity.ok(service.borrowEquipment(id, request.firstName(), request.lastName()));
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<Equipment> returnEquipment(@PathVariable Long id) {
        return ResponseEntity.ok(service.returnEquipment(id));
    }
}