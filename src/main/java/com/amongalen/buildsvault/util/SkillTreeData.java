package com.amongalen.buildsvault.util;

import com.amongalen.buildsvault.model.tree.NodeGroup;
import com.amongalen.buildsvault.model.tree.NodeSize;
import com.amongalen.buildsvault.model.tree.PassiveTreeData;
import com.amongalen.buildsvault.model.tree.TreeNode;
import com.amongalen.buildsvault.model.tree.TreeNodeRepresentation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@Slf4j
public class SkillTreeData {
    private static final Pattern JSON_PREFIX = Pattern.compile(".*=");
    private PassiveTreeData passiveTreeData;
    private final List<TreeNodeRepresentation> nodeRepresentations = new ArrayList<>();

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
        if (nodeRepresentations.isEmpty()) {
            initNodeRepresentations();
        }
    }

    public List<TreeNodeRepresentation> getNodeRepresentationsForTakenNodes(List<TreeNode> takenNodes) {
        return nodeRepresentations.stream().map(node -> {
            boolean isTaken = takenNodes.stream().anyMatch(takenNode -> takenNode.getId().equals(node.getId()));
            return new TreeNodeRepresentation(node.getId(), node.getPositionXY(), node.getSize(), isTaken);
        }).collect(Collectors.toList());
    }

    private void initNodeRepresentations() {
        Collection<NodeGroup> allGroups = getAllGroups();
        Map<Integer, TreeNode> allNodes = getAllNodes();

        for (NodeGroup group : allGroups) {
            List<Integer> nodeIdsInGroup = group.getNodeIds();
            for (Integer nodeId : nodeIdsInGroup) {
                TreeNode node = allNodes.get(nodeId);
                Pair<Double, Double> nodePosition = calculateNodePosition(group, node);
                NodeSize nodeSize = calculateNodeSize(node);
                TreeNodeRepresentation nodeRepresentation = new TreeNodeRepresentation(nodeId, nodePosition, nodeSize, false);
                nodeRepresentations.add(nodeRepresentation);
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

    public Map<Integer, TreeNode> getAllNodes() {
        return passiveTreeData.getNodes();
    }

    public Collection<NodeGroup> getAllGroups() {
        return passiveTreeData.getGroups().values();
    }

    public Integer getNodesPerOrbit(int orbit) {
        return passiveTreeData.getSkillsPerOrbit().get(orbit);
    }

    public Integer getRadiusForOrbit(int orbit) {
        return passiveTreeData.getOrbitRadii().get(orbit);
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

    private static NodeSize calculateNodeSize(TreeNode node) {
        if (node.isKeystone()) {
            return NodeSize.BIG;
        }
        if (node.isNotable()) {
            return NodeSize.MEDIUM;
        }
        return NodeSize.SMALL;
    }


    private Pair<Double, Double> calculateNodePosition(NodeGroup group, TreeNode node) {
        int orbitIndex = node.getOrbitIndex();
        int orbitNumber = node.getOrbitRadii();
        Integer nodesPerOrbit = getNodesPerOrbit(orbitNumber);
        Integer orbitRadius = getRadiusForOrbit(orbitNumber);
        double middleX = group.getX();
        double middleY = group.getY();
        double angleDegrees = 360d - ((360d / nodesPerOrbit) * orbitIndex - 90d);
        double angleRadians = Math.toRadians(angleDegrees);
        double resultX = Math.cos(angleRadians) * orbitRadius + middleX;
        double resultY = Math.sin(angleRadians) * orbitRadius + middleY;
        return Pair.of(resultX, resultY);
    }
}
