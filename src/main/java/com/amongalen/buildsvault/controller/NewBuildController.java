package com.amongalen.buildsvault.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NewBuildController {
    @RequestMapping({"newBuild"})
    public String getIndexPage(Model model) {
        return "newBuild";
    }
}
