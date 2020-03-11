package com.amongalen.buildsvault.util;

import com.amongalen.buildsvault.model.tree.NodeGroup;
import com.amongalen.buildsvault.model.tree.NodeSize;
import com.amongalen.buildsvault.model.tree.PassiveTreeData;
import com.amongalen.buildsvault.model.tree.TreeNode;
import com.amongalen.buildsvault.model.tree.TreeNodeRepresentation;
import com.amongalen.buildsvault.model.tree.TreePathRepresentation;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@Slf4j
public class SkillTreeData {
    private static final Pattern JSON_PREFIX = Pattern.compile(".*=");
    private PassiveTreeData passiveTreeData;
    private final Map<Integer, TreeNodeRepresentation> nodeRepresentations = new HashMap<>();
    private final List<TreePathRepresentation> pathRepresentations = new ArrayList<>();

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
            initPathRepresentations();
        }
    }

    public Map<Integer, TreeNodeRepresentation> getNodeRepresentationsWithTakenNodes(List<TreeNode> takenNodes) {
        Map<Integer, TreeNodeRepresentation> result = new HashMap<>(nodeRepresentations);
        for (TreeNode takenNode : takenNodes) {
            result.computeIfPresent(takenNode.getId(), (k, v) -> new TreeNodeRepresentation(v.getId(), v.getPositionXY(), v.getSize(), true));
        }
        return result;
    }

    public List<TreePathRepresentation> getPathRepresentationsWithTakenNodes(List<TreeNode> takenNodes) {
        ArrayList<TreePathRepresentation> result = new ArrayList<>(pathRepresentations.size());
        List<Integer> takenNodeIds = takenNodes.stream().map(TreeNode::getId).collect(Collectors.toList());
        for (TreePathRepresentation pathRepresentation : pathRepresentations) {
            boolean isTaken = false;
            if (takenNodeIds.contains(pathRepresentation.getStartId()) && takenNodeIds.contains(pathRepresentation.getEndId())) {
                isTaken = true;
            }
            TreePathRepresentation treePathRepresentationWithTakenNodes = TreePathRepresentation.builder()
                    .startId(pathRepresentation.getStartId())
                    .endId(pathRepresentation.getEndId())
                    .startPosition(pathRepresentation.getStartPosition())
                    .endPosition(pathRepresentation.getEndPosition())
                    .isCurve(pathRepresentation.isCurve())
                    .radius(pathRepresentation.getRadius())
                    .isTaken(isTaken)
                    .build();
            result.add(treePathRepresentationWithTakenNodes);
        }

        return result;
    }

    private void initNodeRepresentations() {
        Collection<NodeGroup> allGroups = getAllGroups();
        Map<Integer, TreeNode> allNodes = getAllNodes();

        for (NodeGroup group : allGroups) {
            List<Integer> nodeIdsInGroup = group.getNodeIds();
            for (Integer nodeId : nodeIdsInGroup) {
                TreeNode node = allNodes.get(nodeId);
                if (!node.isMastery() && node.getStartingNodes().length == 0) {
                    Pair<Double, Double> nodePosition = calculateNodePosition(group, node);
                    NodeSize nodeSize = calculateNodeSize(node);
                    TreeNodeRepresentation nodeRepresentation = new TreeNodeRepresentation(nodeId, nodePosition, nodeSize, false);
                    nodeRepresentations.put(nodeId, nodeRepresentation);
                }
            }
        }
    }

    private void initPathRepresentations() {
        for (TreeNode node : passiveTreeData.getNodes().values()) {
            if (!node.isMastery() && node.getStartingNodes().length == 0) {
                for (int connectedNodeId : node.getConnectedNodes()) {
                    TreeNode connectedNode = passiveTreeData.getNodes().get(connectedNodeId);
                    if (areConnected(node, connectedNode)) {
                        Pair<Double, Double> startPosition = nodeRepresentations.get(node.getId()).getPositionXY();
                        Pair<Double, Double> endPosition = nodeRepresentations.get(connectedNodeId).getPositionXY();
                        NodeGroup startingNodeGroup = getNodeGroupForNodeId(node.getId());
                        NodeGroup endNodeGroup = getNodeGroupForNodeId(connectedNodeId);
                        boolean curvedPath = startingNodeGroup.equals(endNodeGroup) && node.getOrbitRadii() == connectedNode.getOrbitRadii();
                        int radius = getRadiusForOrbit(node.getOrbitRadii());
                        TreePathRepresentation pathRepresentation = TreePathRepresentation.builder()
                                .startId(node.getId())
                                .endId(connectedNodeId)
                                .startPosition(startPosition)
                                .endPosition(endPosition)
                                .isCurve(curvedPath)
                                .radius(radius)
                                .build();
                        pathRepresentations.add(pathRepresentation);
                    }
                }
            }
        }
    }

    private static boolean areConnected(TreeNode node, TreeNode connectedNode) {
        return !node.isMastery()
                && node.getStartingNodes().length == 0
                && !connectedNode.isMastery()
                && connectedNode.getStartingNodes().length == 0
                && Objects.equals(connectedNode.getAscendancyName(), node.getAscendancyName());
    }

    public Map<String, Double> getTreeDimensions() {
        Map<String, Double> dimensions = new HashMap<>();
        Double minX = passiveTreeData.getMinX();
        Double minY = passiveTreeData.getMinY();
        double xSize = passiveTreeData.getMaxX() - minX;
        double ySize = passiveTreeData.getMaxY() - minY;
        dimensions.put("minX", minX);
        dimensions.put("minY", minY);
        dimensions.put("xSize", xSize);
        dimensions.put("ySize", ySize);
        return dimensions;
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
        double angleDegrees = ((360d / nodesPerOrbit) * orbitIndex - 90d);
        double angleRadians = Math.toRadians(angleDegrees);
        double resultX = Math.cos(angleRadians) * orbitRadius + middleX;
        double resultY = Math.sin(angleRadians) * orbitRadius + middleY;
        return Pair.of(resultX, resultY);
    }
}
