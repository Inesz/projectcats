package com.cats;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CatsController {

    @RequestMapping("/")
    public String indexView(Model model) {
        return "index";
    }
}