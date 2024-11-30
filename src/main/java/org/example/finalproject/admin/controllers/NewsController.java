package org.example.finalproject.controllers.admin_controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {
    @GetMapping("/news")
    public String news() {
        return "/admin-view/news/news";
    }
}