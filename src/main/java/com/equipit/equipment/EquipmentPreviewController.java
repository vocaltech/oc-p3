package com.equipit.equipment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/preview/equipment")
public class EquipmentPreviewController {

    private final EquipmentService service;

    public EquipmentPreviewController(EquipmentService service) {
        this.service = service;
    }

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("title", "Liste du matériel");
        model.addAttribute("equipment", service.findAll());
        return "equipment/list";
    }

    @GetMapping("/borrow/{id}")
    public String borrowForm(@PathVariable Long id, Model model) {
        model.addAttribute("equipment", service.findById(id));
        return "equipment/borrow";
    }
}
