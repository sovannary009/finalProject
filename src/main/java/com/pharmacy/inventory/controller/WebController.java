package com.pharmacy.inventory.controller;

import com.pharmacy.inventory.entity.Medicine;
import com.pharmacy.inventory.service.MedicineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/medicines")
public class WebController {

    private final MedicineService service;

    public WebController(MedicineService service) {
        this.service = service;
    }

    @GetMapping
    public String viewPage(@RequestParam(required = false) String keyword, Model model) {

        List<Medicine> medicines;

        if (keyword != null && !keyword.isEmpty()) {
            medicines = service.search(keyword);
        } else {
            medicines = service.getAll();
        }

        model.addAttribute("medicines", medicines);
        model.addAttribute("keyword", keyword);
        model.addAttribute("today", java.time.LocalDate.now());

        return "medicines";
    }

    @PostMapping("/save")
    public String saveMedicine(Medicine medicine,RedirectAttributes redirectAttributes) {
        service.save(medicine);
        redirectAttributes.addFlashAttribute("successMessage", "Medicine added successfully!");
        return "redirect:/medicines";
    }

    @GetMapping("/delete/{id}")
    public String deleteMedicine(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        service.delete(id);
        redirectAttributes.addFlashAttribute("successMessage","Medicine deleted successfully!");
        return "redirect:/medicines";
    }

    @GetMapping("/edit/{id}")
    public String editMedicine(@PathVariable Long id, Model model) {
        Medicine medicine = service.getById(id);
        model.addAttribute("medicine", medicine);
        return "edit-medicine";
    }

    @PostMapping("/update")
    public String updateMedicine(@ModelAttribute Medicine medicine, RedirectAttributes redirectAttributes) {
        service.save(medicine);
        redirectAttributes.addFlashAttribute("successMessage","Medicine updated successfully!");
        return "redirect:/medicines";
    }
}