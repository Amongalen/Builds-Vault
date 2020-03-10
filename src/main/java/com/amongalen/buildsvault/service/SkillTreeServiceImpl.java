package com.amongalen.buildsvault.service;

import com.amongalen.buildsvault.model.tree.TreeNode;
import com.amongalen.buildsvault.model.tree.TreeNodeRepresentation;
import com.amongalen.buildsvault.model.tree.TreePathRepresentation;
import com.amongalen.buildsvault.util.SkillTreeData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class SkillTreeServiceImpl implements SkillTreeService {

    private final SkillTreeData skillTreeData;

    @Override
    public Map<Integer, TreeNodeRepresentation> getTreeNodeRepresentations(List<TreeNode> takenNodes) {
        return skillTreeData.getNodeRepresentationsWithTakenNodes(takenNodes);
    }

    @Override
    public List<TreePathRepresentation> getTreePathRepresentation(List<TreeNode> takenNodes) {
        return skillTreeData.getPathRepresentationsWithTakenNodes(takenNodes);
    }
}
