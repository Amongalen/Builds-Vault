package com.amongalen.buildsvault;

import com.amongalen.buildsvault.importer.TreeImporter;
import com.amongalen.buildsvault.importer.TreeImporterImpl;
import com.amongalen.buildsvault.util.SkillTreeData;
import org.springframework.context.annotation.Bean;

public class SpringTestConfiguration {
    @Bean
    public TreeImporter treeImporter() {
        String filename = "390_V2/data.txt";
        SkillTreeData skillTreeData = new SkillTreeData(filename);
        return new TreeImporterImpl(skillTreeData);
    }
}
