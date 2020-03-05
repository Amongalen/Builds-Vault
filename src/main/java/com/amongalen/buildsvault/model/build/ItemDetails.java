package com.amongalen.buildsvault.model.build;

import lombok.Data;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class ItemDetails {

    private String rarity;
    private String name;
    private String baseType;
    private String influence;
    private String itemLevel;
    private String quality;
    private String levelReq;
    private String requirements;
    private String sockets;
    private int implicitAmount;
    private List<String> implicits = new ArrayList<>();
    private List<String> mods = new ArrayList<>();

    public ItemDetails(String content) {
        String escaped = StringUtils.escapeXml(content);
        String trimmed = escaped.trim();
        String[] lines = trimmed.split("\n");
        rarity = lines[0];
        name = lines[1];
        baseType = lines[2];
        int count = 3;

        while (true) {
            String line = lines[count];
            count++;
            if (line.startsWith("Item Level")) {
                itemLevel = line;
            } else if (line.startsWith("Quality")) {
                quality = line;
            } else if (line.startsWith("Sockets")) {
                sockets = line;
            } else if (line.startsWith("LevelReq")) {
                levelReq = line;
            } else if (line.startsWith("Implicits")) {
                implicitAmount = Integer.parseInt(line.replace("Implicits: ", ""));
                break;
            } else if (!line.startsWith("Unique ID")){
                influence = line;
            }
        }
        int currentLine = count;
        while (count < currentLine + implicitAmount) {
            String line = lines[count];
            implicits.add(line);
            count++;
        }

        while (count < lines.length) {
            String line = lines[count];
            mods.add(line);
            count++;
        }
    }
}
