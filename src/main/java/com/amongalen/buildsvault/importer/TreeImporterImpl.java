package com.amongalen.buildsvault.importer;

import com.amongalen.buildsvault.model.build.Skill;
import com.amongalen.buildsvault.model.build.TreeNode;
import com.amongalen.buildsvault.util.SkillTreeData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class TreeImporterImpl implements TreeImporter {

    @Autowired
    SkillTreeData skillTreeData;

    private static final Pattern TREE_LINK_PREFIX = Pattern.compile(".*passive-skill-tree/");

    @Override
    public List<TreeNode> importTreeKeystones(String treeUrl) {
        String raw = TREE_LINK_PREFIX.matcher(treeUrl).replaceAll("");
        List<Integer> nodeIds = parseNodeIdsFromTreeUrl(raw);
        List<TreeNode> treeKeystones = new ArrayList<>();
        if (nodeIds != null) {
            for (Integer nodeId : nodeIds) {
                if (skillTreeData.isKeystone(nodeId)) {
                    String nodeName = skillTreeData.getNameIfKeystone(nodeId);
                    TreeNode treeNode = new TreeNode(nodeId, nodeName);
                    treeKeystones.add(treeNode);
                }
            }
        }
        return treeKeystones;
    }

    static List<Integer> parseNodeIdsFromTreeUrl(String rawUrl) {
        try {
            String replaced = rawUrl.replace('-', '+').replace('_', '/');
            byte[] byteValuesBase64Decoded = Base64.getDecoder().decode(replaced);
            ByteBuffer byteBuffer = ByteBuffer.wrap(byteValuesBase64Decoded);
//            int version = byteBuffer.getInt();
//            byte charactersId = byteBuffer.get();
//            byte ascendancyId = byteBuffer.get();
//            byte isLocked = byteBuffer.get();
            byteBuffer.position(7);
            CharBuffer charBuffer = byteBuffer.asCharBuffer();
            List<Integer> nodes = charBuffer.chars().boxed().collect(Collectors.toList());
            return nodes;
        } catch (IllegalArgumentException e) {
            log.error("Something went wrong while parsing skill tree url", e);
            return null;
        }
    }
}
