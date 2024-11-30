package org.example.finalproject.controllers.admin_controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChannelsController {
    @GetMapping("/channels")
    public String allChannels() {
        return "/admin-view/channels/all-channels";
    }
    @GetMapping("/sport-channels")
    public String sportchannels() {
        return "/admin-view/channels/sport";
    }

    @GetMapping("/movie-channels")
    public String movieChannels() {
        return "/admin-view/channels/movie";
    }

    @GetMapping("/news-channels")
    public String newsChannels() {
        return "/admin-view/channels/news";
    }
    @GetMapping("/music-channels")
    public String musicChannels() {
        return "/admin-view/channels/music";
    }
}
