package org.example.finalproject.admin.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.dtos.admin.packages.PackageRegistrationDto;
import org.example.finalproject.admin.helpers.files.FileHelper;
import org.example.finalproject.admin.models.admin.AdminRole;
import org.example.finalproject.admin.models.admin.PackageEnum;
import org.example.finalproject.admin.services.implementations.PackageServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin-panel/packages")
@RequiredArgsConstructor
public class PackagesController {
    private final PackageServiceImplementation service;
    private final FileHelper fileHelper;

    @GetMapping("")
    public String packages(Model model) {
        model.addAttribute("roles", AdminRole.class);
        model.addAttribute("packagesList", service.findAll());
        return "admin-view/packages/list";
    }

    @GetMapping("/{id}/details")
    public String details(@PathVariable long id, Model model) {
        model.addAttribute("package", service.findById(id));
        return "admin-view/packages/details";
    }

    @GetMapping("/new")
    public String addNewPackage(Model model) {
        model.addAttribute("packageRegistrationDto", new PackageRegistrationDto());
        model.addAttribute("packagesList", PackageEnum.values());
        return "admin-view/packages/new";
    }

    @PostMapping("/new")
    public String addNewPackage(@Valid @ModelAttribute PackageRegistrationDto packageRegistrationDto,
                                @RequestParam("photoFile") MultipartFile photoFile,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return "admin-view/packages/new";
        }
        if (!photoFile.isEmpty()) {
            try {
                var fileName = fileHelper.uploadFile("target/classes/static/assets-a/img", photoFile.getOriginalFilename(), photoFile.getBytes());
                packageRegistrationDto.setPhoto("/assets-a/img/" + fileName);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        redirectAttributes.addFlashAttribute("addedMessage", "Package added successfully!");
        service.add(packageRegistrationDto);
        return "redirect:/admin-panel/packages";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("packageRegistrationDto", service.findById(id));
        model.addAttribute("packagesList", PackageEnum.values());

        return "admin-view/packages/edit";
    }

    @PostMapping("/{id}/edit")
    public String modifyPackage(@Valid @ModelAttribute PackageRegistrationDto packageRegistrationDto,
                                BindingResult bindingResult,
                                @RequestParam("photoFile") MultipartFile photoFile,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return "admin-view/packages/edit";
        }
        if (!photoFile.isEmpty()) {
            try {
                var fileName = fileHelper.uploadFile("target/classes/static/assets-a/img", photoFile.getOriginalFilename(), photoFile.getBytes());
                packageRegistrationDto.setPhoto("/assets-a/img/" + fileName);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        redirectAttributes.addFlashAttribute("editedMessage", "Package with id: " + packageRegistrationDto.getId() + " modified successfully!");

        service.modify(packageRegistrationDto, packageRegistrationDto.getId());
        return "redirect:/admin-panel/packages";
    }


    @PostMapping("/{id}/delete")
    public String deletePackage(@PathVariable long id, RedirectAttributes redirectAttributes) {
        service.removeById(id);
        redirectAttributes.addFlashAttribute("deletedMessage", "Package deleted successfully!");
        return "redirect:/admin-panel/packages";
    }


}
