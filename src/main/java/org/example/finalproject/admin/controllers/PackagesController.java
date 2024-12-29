package org.example.finalproject.admin.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.dtos.admin.packages.PackageRegistrationDto;
import org.example.finalproject.admin.services.implementations.PackageServiceImplementation;
import org.example.finalproject.admin.services.interfaces.PackageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin-af/packages")
@RequiredArgsConstructor
public class PackagesController {
    private final PackageServiceImplementation service;

    @GetMapping("")
    public String packages(Model model) {
        model.addAttribute("packagesList", service.findAll());
        return "admin-view/packages/list";
    }

    @GetMapping("/{id}/details")
    public String details(@PathVariable long id, Model model) {
        model.addAttribute("package", service.findById(id));
        return "admin-view/packages/details";
    }

    @GetMapping("/add-new-package")
    public String addNewPackage(Model model) {
        model.addAttribute("packageRegistrationDto", new PackageRegistrationDto());
        return "admin-view/packages/new";
    }

    @PostMapping("/add-new-package")
    public String addNewPackage(@Valid @ModelAttribute PackageRegistrationDto packageRegistrationDto,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return "admin-view/packages/new";
        }
        redirectAttributes.addFlashAttribute("addedMessage", "Package added successfully!");
        service.add(packageRegistrationDto);
        return "redirect:/admin-af/packages";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("packageRegistrationDto", service.findById(id));
        return "admin-view/packages/edit";
    }

    @PostMapping("/{id}/edit")
    public String modifyPackage(@Valid @ModelAttribute PackageRegistrationDto packageRegistrationDto,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return "admin-view/packages/edit";
        }

        redirectAttributes.addFlashAttribute("editedMessage", "Package modified successfully!");

        service.modify(packageRegistrationDto, packageRegistrationDto.getId());
        return "redirect:/admin-af/packages";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable long id, Model model) {
        model.addAttribute("package", service.findById(id));

        return "admin-view/packages/delete";
    }

    @PostMapping("/{id}/delete")
    public String deletePackage(@PathVariable long id, RedirectAttributes redirectAttributes) {
        service.removeById(id);
        redirectAttributes.addFlashAttribute("deletedMessage", "Package deleted successfully!");
        return "redirect:/admin-af/packages";
    }


}
