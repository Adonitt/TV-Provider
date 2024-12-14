package org.example.finalproject.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-af")

public class WebPageController {

    @GetMapping("/most-visited")
    public String mostVisitedPage() {
        return "admin-view/web-page-details/most-visited/most-visited";
    }

    @GetMapping("/reviews")
    public String webPageReviews() {
        return "admin-view/web-page-details/reviews/reviews";

    }
}
