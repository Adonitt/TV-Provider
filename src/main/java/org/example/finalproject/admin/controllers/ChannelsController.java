package org.example.finalproject.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-af")

public class ChannelsController {

    @GetMapping("/channels")
    public String allChannels() {
        return "admin-view/channels/all-channels/all-channels";
    }

    @GetMapping("/sport-channels")
    public String sportchannels() {
        return "admin-view/channels/sport/sport";
    }

    @GetMapping("/movie-channels")
    public String movieChannels() {
        return "admin-view/channels/movie/movie";
    }

    @GetMapping("/news-channels")
    public String newsChannels() {
        return "admin-view/channels/news/news";
    }

    @GetMapping("/music-channels")
    public String musicChannels() {
        return "admin-view/channels/music/music";
    }
}
