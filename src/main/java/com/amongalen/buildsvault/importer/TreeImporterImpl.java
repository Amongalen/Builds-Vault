package com.amongalen.buildsvault.importer;

import com.amongalen.buildsvault.model.tree.TreeNode;
import com.amongalen.buildsvault.util.SkillTreeData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class TreeImporterImpl implements TreeImporter {

    final SkillTreeData skillTreeData;

    @Override
    public List<TreeNode> importTreeNodes(String treeUrl) {
        String[] urlParts = treeUrl.split("/");
        String urlEnd = urlParts[urlParts.length - 1];
        if (urlEnd.isBlank()) {
            urlEnd = urlParts[urlParts.length - 2];
        }
        List<Integer> nodeIds = parseNodeIdsFromTreeUrl(urlEnd);
        return nodeIds.stream().map(skillTreeData::getTreeNodeForNodeId).collect(Collectors.toList());
    }

    private static List<Integer> parseNodeIdsFromTreeUrl(String rawUrl) {
        try {
            String replaced = rawUrl.replace('-', '+').replace('_', '/').trim();
            byte[] byteValuesBase64Decoded = Base64.getDecoder().decode(replaced);
            ByteBuffer byteBuffer = ByteBuffer.wrap(byteValuesBase64Decoded);
            byteBuffer.position(7);
            CharBuffer charBuffer = byteBuffer.asCharBuffer();
            List<Integer> nodes = charBuffer.chars().boxed().collect(Collectors.toList());
            return nodes;
        } catch (IllegalArgumentException e) {
            log.error("Something went wrong while parsing skill tree url", e);
            return Collections.emptyList();
        }
    }
}
