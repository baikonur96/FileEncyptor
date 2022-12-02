package com.skillbox.java;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class GUIForme {
    private JPanel panel1;
    private JTextField filePath;
    private JButton selectButton;
    private JButton actionButton;
    private File selectedFile;

    private boolean encryptedFileSelected = false;
    private String decryptAction = "Расшифровать";
    private String encryptAction = "Зашифровать";
    private ZipParameters parameters;
    public GUIForme(){

        parameters = new ZipParameters();

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
       // parameters
       //         .setPassword("123");
        selectButton.addActionListener(new Action(){
            public Object getValue(String key) {
                return null;
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                chooser.showOpenDialog(panel1);
                selectedFile = chooser.getSelectedFile();
                if (selectedFile == null) {
                    filePath.setText("");
                    actionButton.setVisible(false);
                    return;
                }
                filePath.setText(selectedFile.getAbsolutePath());
                try {
                    ZipFile zipFile = new ZipFile(selectedFile);
                    encryptedFileSelected = zipFile.isValidZipFile() && zipFile.isEncrypted();
                        actionButton.setText( encryptedFileSelected ?
                                decryptAction : encryptAction);

                }catch (Exception ex) {
                    ex.printStackTrace();
                }
                actionButton.setVisible(true);

            }


            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }
        });
        actionButton.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedFile == null){
                    return;
                }
                setButtonEnable(false);
                String password = JOptionPane.showInputDialog("Введите пароль:");
                if (encryptedFileSelected){
                    decryptFile(password);
                }else {
                    encryptFile(password);
                }
                setButtonEnable(true);

            }
        });
    }
    public JPanel getRootPanel() {
        return panel1;
    }

    private void setButtonEnable(boolean enabled) {
        selectButton.setEnabled(enabled);
        actionButton.setEnabled(enabled);

    }
    private void encryptFile(String password) {
        String archiveName = getArchiveName();
        parameters.setPassword(password);
        try {
            ZipFile zipFile = new ZipFile(archiveName);

           if (selectedFile.isDirectory()){
               zipFile.addFolder(selectedFile, parameters);
           }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    private void decryptFile(String password) {
        String outPath = getOutputPath();
        try {
            ZipFile zipFile = new ZipFile(selectedFile);
            zipFile.setPassword(password);
            zipFile.extractAll(outPath);
        } catch (ZipException e) {
           e.printStackTrace();
        }

    }

    private String getArchiveName() {
        for (int i = 1; ; i++){
            String number  = i > 0 ? Integer.toString(i) : "";
            String archiveName = selectedFile.getAbsolutePath() + number + ".zip";
            if (!new File(archiveName).exists()) {
                return archiveName;
            }
        }
    }
    private String getOutputPath() {
        String path = selectedFile.getAbsolutePath().replaceAll("\\.zip$", "");
        for (int i = 1; ; i++) {
            String number = i > 1 ? Integer.toString(i) : "";
            if (new File(path + number).exists()) {
                return path;
            }
        }

    }

}
