package com.equipit.equipment.repository;

import com.equipit.equipment.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> { }