package com.amongalen.buildsvault.importer;

import com.amongalen.buildsvault.SpringTestConfiguration;
import com.amongalen.buildsvault.model.tree.TreeNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringTestConfiguration.class)
class TreeImporterImplTest {
    @Autowired
    TreeImporter treeImporter;

    @Test
    void importTreeKeystones() {
        String link = "https://www.pathofexile.com/passive-skill-tree/AAAABAYBABzcz3qiABa_qM_Zhgj0UXQ8BWHrjxrnVI19elM6WLzqakPvfEqLN9T_3hCSd-Xw1aKjbAtFR2yMtDi5fFFHwGZtbAQHshkmlVXGOkIPxCL0LJww-O-I-JfwHyT91CNTUu_rF7DVpvv1lPFNkh9BBLM9XyoLVUuMNkyztUgj9jB8SbFJUQce7T-D292oYeLXz5UuuMq-iujWmyZFnX_Gl_R8g5eVgptwbm0ZUDCkBa7_Oti8b42_MtGMCxEtidM2PZBVMgEgbnBSEZZLeI7pGjidqjBxMjKa4JhT-TfQH-d0O3wFtQ5IXfJSU-UZ7LChLycvBuemmWKs6-6TJw==";
        List<TreeNode> treeKeystones = treeImporter.importTreeNodes(link);
        assertEquals(2, treeKeystones.size());
    }
}