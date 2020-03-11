package com.amongalen.buildsvault.controller;

import com.amongalen.buildsvault.model.BuildGuide;
import com.amongalen.buildsvault.model.tree.TreeNode;
import com.amongalen.buildsvault.service.BuildGuideService;
import com.amongalen.buildsvault.service.SkillTreeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class BuildGuideDetailsController {
    private final BuildGuideService buildGuideService;
    private final SkillTreeService skillTreeService;

    @RequestMapping("buildGuide")
    public String getIndexPage(String id, Model model) {
        Optional<BuildGuide> guideOptional = buildGuideService.getGuideById(id);

        BuildGuide guide = guideOptional.orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        model.addAttribute("guide", guide);

        List<TreeNode> takenNodes = guide.getTreeNodes();
        String treeRepresentation = skillTreeService.getTreeRepresentation(takenNodes);
        model.addAttribute("tree", treeRepresentation);
        return "buildGuide";
    }
}
