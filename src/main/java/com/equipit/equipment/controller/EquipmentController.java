package com.equipit.equipment.controller;

import com.equipit.service.EquipmentService;
import com.equipit.equipment.dto.BorrowRequest;
import com.equipit.equipment.model.Equipment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/equipment")
@Tag(name = "equipment-controller", description = "List all equipments, borrow and return an equipment")
public class EquipmentController {
    private final EquipmentService service;

    public EquipmentController(EquipmentService service) {
        this.service = service;
    }

    /**
     * Find all equipments
     * @return - A list of all equipments
     */
    @GetMapping
    @Operation(summary = "List all equipments")
    public List<Equipment> findAll() {
        return service.findAll();
    }

    /**
     * Borrow an equipment
     * @param id - The id of the equipment
     * @param request - The borrow request
     * @return - The borrowed id equipment, firstname and lastname borrower
     */
    @PostMapping("/{id}/borrow")
    @Operation(summary = "Borrows an equipment")
    public ResponseEntity<Equipment> borrow(
            @PathVariable
            @Parameter(description = "The equipment ID")
            Long id,
            @Valid
            @RequestBody
            BorrowRequest request
    ) {
        return ResponseEntity.ok(service.borrowEquipment(id, request.firstName(), request.lastName()));
    }

    /**
     * Return an equipment
     * @param id - The id of the equipment
     * @return - The returned id equipment
     */
    @PostMapping("/{id}/return")
    @Operation(summary = "Returns an equipment")
    public ResponseEntity<Equipment> returnEquipment(
            @PathVariable
            @Parameter(description = "The equipment ID")
            Long id
    ) {
        return ResponseEntity.ok(service.returnEquipment(id));
    }
}