package com.equipit.equipment.controller;

import com.equipit.service.EquipmentService;
import com.equipit.equipment.dto.BorrowRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/preview/equipment")
public class EquipmentPreviewController {
    private static final String EQUIPMENT = "equipment";
    private final EquipmentService service;

    public EquipmentPreviewController(EquipmentService service) {
        this.service = service;
    }

    @GetMapping("")
    public String showEquipmentList(Model model) {
        model.addAttribute("title", "Liste du matériel");
        model.addAttribute(EQUIPMENT, service.findAll());
        return "equipment/list";
    }

    @GetMapping("/borrow/{id}")
    public String showBorrowForm(@PathVariable Long id, Model model) {
        model.addAttribute(EQUIPMENT, service.findById(id));
        model.addAttribute("borrowRequest", new BorrowRequest("", ""));
        return "equipment/borrow";
    }

    @PostMapping("/borrow/{id}")
    public String processBorrow(@PathVariable Long id, @Valid @ModelAttribute BorrowRequest borrowRequest) {
        service.borrowEquipment(id, borrowRequest.firstName(), borrowRequest.lastName());
        return "redirect:/preview/equipment";
    }

    @GetMapping("/return/{id}")
    public String showReturnForm(@PathVariable Long id, Model model) {
        model.addAttribute(EQUIPMENT, service.findById(id));
        return "equipment/return";
    }

    @PostMapping("/return/{id}")
    public String processReturn(@PathVariable Long id) {
        service.returnEquipment(id);
        return "redirect:/preview/equipment";
    }
}
