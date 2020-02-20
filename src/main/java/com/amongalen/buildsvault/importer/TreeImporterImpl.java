package com.amongalen.buildsvault.importer;

import com.amongalen.buildsvault.model.build.TreeNode;
import com.amongalen.buildsvault.util.SkillTreeData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class TreeImporterImpl implements TreeImporter {

    final SkillTreeData skillTreeData;

    @Override
    public List<TreeNode> importTreeKeystones(String treeUrl) {
        String[] urlParts = treeUrl.split("/");
        String urlEnd = urlParts[urlParts.length - 1];
        if (urlEnd.isBlank()) {
            urlEnd = urlParts[urlParts.length - 2];
        }
        List<Integer> nodeIds = parseNodeIdsFromTreeUrl(urlEnd);
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

    private static List<Integer> parseNodeIdsFromTreeUrl(String rawUrl) {
        try {
            String replaced = rawUrl.replace('-', '+').replace('_', '/').trim();
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
