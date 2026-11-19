package com.equipit.equipment.service;

import java.time.LocalDate;
import java.util.List;

import com.equipit.equipment.model.Equipment;
import com.equipit.equipment.repository.EquipmentRepository;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {
    private final EquipmentRepository repository;

    public EquipmentService(EquipmentRepository repository) {
        this.repository = repository;
    }

    public List<Equipment> findAll() {
        return repository.findAll();
    }

    public Equipment findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Unknown equipment: " + id));
    }

    /**
     * Logique Métier: Emprunter un équipement
     * @param id - The equipment ID
     * @param firstName - The borrower first name
     * @param lastName - The borrower last name
     * @return
     */
    public Equipment borrowEquipment(Long id, String firstName, String lastName) {
        Equipment equipment = findById(id);
        if (!equipment.isAvailable()) {
            throw new IllegalStateException("Equipment already borrowed: " + id);
        }
        equipment.setAvailable(false);
        equipment.setBorrowerFirstName(firstName);
        equipment.setBorrowerLastName(lastName);
        equipment.setBorrowedAt(LocalDate.now());
        return repository.save(equipment);
    }

    /**
     * Return an equipment
     * @param id - The equipment ID
     * @return
     */
    public Equipment returnEquipment(Long id) {
        Equipment equipment = findById(id);
        if (equipment.isAvailable()) {
            throw new IllegalStateException("Equipment not borrowed: " + id);
        }
        equipment.setAvailable(true);
        equipment.setLastReturnedAt(LocalDate.now());
        equipment.setBorrowerFirstName(null);
        equipment.setBorrowerLastName(null);
        return repository.save(equipment);
    }
}