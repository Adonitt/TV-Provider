package org.example.finalproject.admin.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.dtos.admin.WebsiteDto;
import org.example.finalproject.admin.helpers.files.FileHelper;
import org.example.finalproject.admin.models.WebsiteEntity;
import org.example.finalproject.admin.models.admin.AdminRole;
import org.example.finalproject.admin.services.interfaces.WebsiteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin-panel/website-details")
@RequiredArgsConstructor
public class WebsiteController {
    private final WebsiteService service;
    private final FileHelper fileHelper;

    @GetMapping("")
    public String getDetails(Model model) {
        WebsiteEntity websiteEntity = service.fetchData();
        if (websiteEntity == null) {
            model.addAttribute("nullMessage", "No website data found in the database.");
        }
        model.addAttribute("website", websiteEntity);
        model.addAttribute("roles", AdminRole.class);
        return "admin-view/web-page-details/website-details/list"; // Update to your single entity view
    }

    @GetMapping("/add")
    public String addDetails(Model model) {
        model.addAttribute("websiteDto", new WebsiteDto());
        return "admin-view/web-page-details/website-details/add";
    }

    @PostMapping("/add")
    public String addDetails(@Valid @ModelAttribute WebsiteDto dto,
                             @RequestParam("photoFile") MultipartFile photoFile,
                             BindingResult br,
                             RedirectAttributes ra) {
        if (br.hasErrors()) {
            br.getAllErrors().forEach(System.out::println);
            return "admin-view/web-page-details/website-details/add";
        }
        uploadPhoto(dto, photoFile);
        service.add(dto);
        return "redirect:/admin-panel/website-details";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("websiteDto", service.findById(id));
        return "admin-view/web-page-details/website-details/edit";
    }

    @PostMapping("{id}/edit")
    public String modify(@Valid @ModelAttribute WebsiteDto dto,
                         BindingResult br,
                         @RequestParam("photoFile") MultipartFile photoFile,
                         RedirectAttributes ra) {
        if (br.hasErrors()) {
            br.getAllErrors().forEach(System.out::println);
            return "admin-view/web-page-details/website-details/edit";
        }

        uploadPhoto(dto, photoFile);

        service.modify(dto, dto.getId());
        return "redirect:/admin-panel/website-details";
    }

    private void uploadPhoto(@ModelAttribute @Valid WebsiteDto dto, @RequestParam("photoFile") MultipartFile photoFile) {
        if (!photoFile.isEmpty()) {
            try {
                var fileName = fileHelper.uploadFile("target/classes/static/assets-a/img", photoFile.getOriginalFilename(), photoFile.getBytes());
                dto.setImage("/assets-a/img/" + fileName);

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            dto.setImage("/images/hero-bg.jpg");
        }
    }
}

