package com.amongalen.buildsvault.util;

import com.amongalen.buildsvault.model.tree.NodeGroup;
import com.amongalen.buildsvault.model.tree.PassiveTreeData;
import com.amongalen.buildsvault.model.tree.TreeNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

@Component
@Slf4j
public class SkillTreeData {
    private static final Pattern JSON_PREFIX = Pattern.compile(".*=");
    PassiveTreeData passiveTreeData;

    public SkillTreeData() {
        String filename = "tree/390_V2/data.txt";
        init(filename);
    }

    public void init(String filename) {
        String json = readJson(filename);
        if (json != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                passiveTreeData = mapper.readValue(json, PassiveTreeData.class);
            } catch (JsonProcessingException e) {
                log.error("Problem occurred while loading skill tree data", e);
            }
        }
    }

    public void printNodeAndGroupForId(Integer id) {
        System.out.println("node: " + getTreeNodeForNodeId(id));
        System.out.println("group: " + getNodeGroupForNodeId(id));
    }

    public NodeGroup getNodeGroupForNodeId(Integer id) {
        return passiveTreeData.getGroups().values().stream().filter(g -> g.getNodeIds().contains(id)).findFirst().orElseThrow();
    }

    public TreeNode getTreeNodeForNodeId(Integer id) {
        return passiveTreeData.getNodes().get(id);
    }

    public SkillTreeData(String filename) {
        init(filename);
    }

    public boolean isKeystone(Integer id) {
        return passiveTreeData.getNodes().get(id).isKeystone();
    }

    public String getNameById(Integer id) {
        return passiveTreeData.getNodes().get(id).getName();
    }


    private static String readJson(String filename) {
        try {
            Resource resource = new ClassPathResource(filename);
            try (InputStream input = resource.getInputStream()) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
                    String line = br.readLine();
                    String result = JSON_PREFIX.matcher(line).replaceFirst("");
                    return result;
                }
            }
        } catch (IOException e) {
            log.error("Problem occured while loading skill tree data", e);
        }
        return null;
    }
}
