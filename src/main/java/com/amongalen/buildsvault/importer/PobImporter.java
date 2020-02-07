package com.amongalen.buildsvault.importer;

import com.amongalen.buildsvault.exporter.CompressionUtils;
import com.amongalen.buildsvault.model.build.PathOfBuilding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@Slf4j
public class PobImporter implements Importer {

    @Override
    public PathOfBuilding importBuild(String link) {
        PathOfBuilding pathOfBuilding = null;
        try {
            String raw = "eNqtWVt32jgQfk5_hQ_vCTEXB3pIewi5lHNCysZpu_vUo5gB1MiS15JD6a_fkWwHNwlhTLcP1Ja_-WY0o7nYGXz8GQvvEVLNlTxt-EfHDQ9kpGZcLk4bX-4uD3uNjx_eDabMLD_PzzIu7JMP7w4G7toT8AgC5RqeYekCzNeSqf0dme6ZnHFzo9KYIehGSSjXJpBGXIDW5XIkmNY3LIbTRhghQ8NjOgI5G23Wc2DMuAxV9ADmKlVZ4nQnTJolKDnhUqVXalZin9bZj9_WHzmsJmqGnOPJ9PPtXWnVKM2gNBT3eDCYCraGNDTMeBp_ThtDdBVbwDmL8ReJmMiQpX3U6zSar0uECcDsCekftf4YOE3hYj6HyPBHGKVo9ZLJaGPM8Ta5uthJJgxPBIe0YlV3m8SnF-T9YBv2ThkmzqfhE7RzFBz33kYrs9vqb9wsp4prJcnkEybZSGkCeWg2fmhtRd3Cv1XgVtw5_KSxVYFbcWNpaGxV4FbcNZ9vohgcv3Fgo_cWO5YRjfSLTEFD-lg55wFRYIoFAyrG-8dvCt7CAiTNqGuAaHmFVeWWGUJm2APzhOq-7RyLJTnHAl9xTpcoUMM5VvCZc476b4FruudCQrpYh0sOYkbIKeulqgjJW1UBYqCrInV39Mi0a0hlUHpv7yaH08IOAgAFZvCsdLZ6_6PINFU_bLcQNeWGaayylBjEHEza9XS51jzCku6a6C3MMjROUYJYdr2JeoQYD7xrmdjINwd_m-iZwJGB2vyQVohaEkNjWPRwrmYLqKWklsQlT9Fbmlea1eH28jlSNjmo6Gu-WBqJo10NBUumNB2-sf4zzlAjlhCGkKctkEWe7YOuarMZssxUrRC5tHOvroeesE1Pb291WAry15rM_xucpOBCzrLUHj2yjucSL9UMmu69wF6N40Slxi2OmIi0oxzLJDOedPO8fuBCfJdZfG8nzPz_TQpXkTHX0ff7bD63Q3sDjUnd68nF5eXF6G789aIQCcEVEi9SQrBEA877kouGx_EitMpCLJ6RIaBxmC1G_N1YO5kS9LvpfjfODt40A4uyQdz63ToBG0lNEDgTNFsxwkxQLMhncgrjQnJDcjpEbE3APfV6AvZCwJAL21coTprgmcwbEgWNDSLl95kBCtjN3wQL7CRK2FZ1IiNYmnd9Am8xF-1GFk2CEFWXc3fsASQxAucwB8wCXRSfstAM3JnX3gzmDN9iryD-K2OCm3Uhvlm_zj9guFW9VKswS2zZwic2YzT64_oan-RL-myNL5anDZNmeYAGdymAx9xQYscgW75yu_HGM_iw8kGk7x4dDL7cXruLg6UxiX7fbK5Wq6OEmaWaw0-c0o4iFTcTpjWSHroieWiZmkP8d2Z_hqenjqhZMg3yDyI6py3uPInFcox-OuljV_Ywr2J7VxT3l7B26yRoE3CtXuekS8EFfj-g6A2CdoeCa_d7fYpev9-j8HWCXq9FwAV-x6fo7Xb81gmFr9WmhKPb9fsUXKd3EvQobun0fdJue6Ro4GZJpyU4Pml3SdEN2hT72p1Oj6K37QckXCs4ab1i36BZSSm8wXR2Bcbmu724UVjK7TO7WN64avCVw8rTwNJoGRqcZxreL6Xifyy1KzCfgJkJS4qSY58VJahdFCBs0uccG0vqCltRbBzw79K-wRjN1UXdsdchGPchNNOAFVPJ2TdgiZJu2erJJ6ocuBXkiqpfFKlQqHL6uhL4uqVf8VEFk1N5PgnVClcs8Yb3a62Z8IqAtN4WPQNhaopcCqYfvPYOXjVbe3nPq0k_jDMBhmLCH3jFp_B367uOxLvDAZ8Ah6W6QSk369nd0s5UTdtLDeS4U1zR2c9UqjP2EvMJPrzFF6XdrlBG1zQhrwl1c3h7rvn7qPP3OZ_-fiHxCT6kRW2PNHdR9PenJyXyHofE3114XiAGzaIPuX7qGpn7RKDknC8QMWg-_1vjf61p0MU=";
            String xml = extractFromPobPastebin(raw);
            JacksonXmlModule xmlModule = new JacksonXmlModule();
            xmlModule.setDefaultUseWrapper(false);
            XmlMapper xmlMapper = new XmlMapper(xmlModule);
            pathOfBuilding = xmlMapper.readValue(Objects.requireNonNull(xml), PathOfBuilding.class);
        } catch (JsonProcessingException e) {
            log.error("Something went wrong while importing PoB build ", e);
        }
        return pathOfBuilding;
    }

    static String extractFromPobPastebin(String raw) {

        byte[] byteValueBase64Decoded;
        try {
            String replace = raw.replace('-', '+').replace('_', '/').trim();
            byteValueBase64Decoded = Base64.getDecoder().decode(replace);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
        String inflatedXml = "";
        try {
            //inflate
            byte[] decompressed = CompressionUtils.decompress(byteValueBase64Decoded);
            inflatedXml = new String(decompressed, StandardCharsets.UTF_8);
        } catch (IOException | DataFormatException e) {
            log.error("Couldn't inflate POB XML. raw: {}", raw);
        }
        return inflatedXml;
    }

}

