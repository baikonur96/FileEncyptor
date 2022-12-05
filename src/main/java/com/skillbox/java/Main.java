package com.skillbox.java;


import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import javax.swing.*;
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception  {

        JFrame frame = new JFrame("File Encrypter");
        frame.setSize(600,400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        GUIForme form = new GUIForme();
        frame.add(form.getPanel1());
        frame.setVisible(true);

        //ZipFile zipFile = new ZipFile("");
        //zipFile.isValidZipFile();

    }
}

/*        String path = "/Users/Diagnost/Desktop/";
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

        ZipFile zipFile1 = new ZipFile(ProgramSettings.outputFolder + ProgramSettigs.outputFilename);
        if (zipFile1.isEncrypted()){
            zipFile1.setPassword(ProgramSettings.getEncryptionPassword());
        }
        zipFile1.extractAll(ProgramSettings.outputDolder + ProgramSettings.decryptOutput);*/



