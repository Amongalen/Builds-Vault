package com.amongalen.buildsvault.controller;

import com.amongalen.buildsvault.model.BuildGuide;
import com.amongalen.buildsvault.service.BuildGuideService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class BuildListController {

    private final BuildGuideService buildGuideService;

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        List<BuildGuide> allGuides = buildGuideService.getAllGuides();
        model.addAttribute("guides", allGuides);
        return "index";
    }
}
