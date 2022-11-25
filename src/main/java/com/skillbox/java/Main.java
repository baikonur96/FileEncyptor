package com.skillbox.java;


import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {

        String path = "/Users/Diagnost/Desktop/";
        ZipParameters parameters = new ZipParameters();
        parameters
                .setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        parameters
                .setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_ULTRA);
        parameters
                .setEncryptFiles(true);
        parameters
                .setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
        parameters
                .setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
        parameters
                .setPassword("123");

        ZipFile zipFile = new ZipFile(path + "archive.zip");
        zipFile.addFolder(new File(path + "folder"), parameters);
     //   zipFile.addFile(new File(path + "IMG_3636.JPG"), parameters);

    }
}