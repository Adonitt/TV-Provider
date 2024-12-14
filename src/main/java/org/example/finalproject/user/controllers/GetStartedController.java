package org.example.finalproject.user.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/get-started")
public class GetStartedController {
    @GetMapping("")
    public String getStarted() {
        return "user-view/get-started/get-started";
    }
}
