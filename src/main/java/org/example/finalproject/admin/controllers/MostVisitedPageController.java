package org.example.finalproject.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-panel/most-visited")

public class MostVisitedPageController {

    @GetMapping("")
    public String mostVisitedPage() {
        return "admin-view/web-page-details/most-visited/most-visited";
    }
}


