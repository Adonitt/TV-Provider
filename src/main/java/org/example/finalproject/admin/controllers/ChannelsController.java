package org.example.finalproject.admin.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.dtos.admin.channels.ChannelsDto;
import org.example.finalproject.admin.mappers.ChannelsMapper;
import org.example.finalproject.admin.repositories.ChannelRepository;
import org.example.finalproject.admin.services.interfaces.ChannelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin-af/channels")
@RequiredArgsConstructor
public class ChannelsController {
    private final ChannelService service;
    private final ChannelRepository repository;

    @GetMapping("")
    public String allChannels(Model model) {
        model.addAttribute("channelsList", service.findAll());
        return "admin-view/channels/list";
    }

    @GetMapping("/new")
    public String addChannel(Model model) {
        model.addAttribute("channelsDto", new ChannelsDto());
        return "admin-view/channels/new";
    }

    @PostMapping("/new")
    public String addChannel(@Valid @ModelAttribute ChannelsDto channelsDto, BindingResult br, RedirectAttributes ra) {
        if (br.hasErrors()) {
            br.getAllErrors().forEach(System.out::println);
            return "admin-view/channels/new";
        }
        ra.addFlashAttribute("addedMessage", "Channel Addded Successfully!");
        service.add(channelsDto);
        return "redirect:/admin-af/channels";
    }

    @GetMapping("/{id}/edit")
    public String editChannel(@PathVariable Integer id, Model model) {
        model.addAttribute("channelsDto", repository.findById(id));
        return "/admin-view/channels/edit";
    }

    @PostMapping("/{id}/edit")
    public String editChannel1(@ModelAttribute ChannelsDto channelsDto, BindingResult br, RedirectAttributes ra) {
        if (br.hasErrors()) {
            br.getAllErrors().forEach(System.out::println);
            return "admin-view/channels/edit";
        }

        ra.addFlashAttribute("editedMessage", "Channel with id " + channelsDto.getId() + " edited successfully!");
        service.modify(channelsDto, channelsDto.getId());
        return "redirect:/admin-af/channels";
    }


    @PostMapping("/{id}/delete")
    public String deleteChannel(@PathVariable Integer id, RedirectAttributes ra) {
        service.removeById(id);
        ra.addFlashAttribute("deletedMessage", "Channel with id " + id + " deleted successfully !");
        return "redirect:/admin-af/channels";
    }
}
