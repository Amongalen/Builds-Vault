package com.amongalen.buildsvault.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

@Component
@Slf4j
public class SkillTreeData {
    private static final Pattern JSON_PREFIX = Pattern.compile(".*=");
    Map<Integer, String> nodeMapping = new HashMap<>();

    public SkillTreeData() {
        String filename = "390_V2/data.txt";
        init(filename);
    }

    public boolean isKeystone(Integer id) {
        return nodeMapping.containsKey(id);
    }

    public String getNameIfKeystone(Integer id) {
        return nodeMapping.get(id);
    }

    public void init(String filename) {
        String json = readJson(filename);
        if (json != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonTree = mapper.readTree(json);
                JsonNode nodes = jsonTree.get("nodes");
                for (Iterator<Map.Entry<String, JsonNode>> it = nodes.fields(); it.hasNext(); ) {
                    Map.Entry<String, JsonNode> node = it.next();
                    JsonNode nodeDetails = node.getValue();
                    String nodeName = nodeDetails.get("dn").asText();
                    Integer nodeId = Integer.valueOf(node.getKey());
                    boolean isKeystone = nodeDetails.get("ks").asBoolean();
                    if (isKeystone) {
                        nodeMapping.put(nodeId, nodeName);
                    }
                }
            } catch (JsonProcessingException e) {
                log.error("Problem occured while loading skill tree data", e);
            }
        }
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
