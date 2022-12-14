package com.skillbox.java;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;

public class GUIForme {
    private JPanel panel1;
    private JTextField filePath;
    private JButton selectButton;
    private JButton actionButton;
    private File selectedFile;

    private boolean encryptedFileSelected = false;
    private String decryptAction = "Расшифровать";
    private String encryptAction = "Зашифровать";
    public GUIForme(){
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
                onFileSelect();
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
                String password = JOptionPane.showInputDialog("Введите пароль:");
                if (password == null || password.length() == 0){
                    showWarning("Пароль не указан");
                    return;
                }
                try {
                    if (encryptedFileSelected) {
                        decryptFile(password);
                    } else {
                        encryptFile(password);
                    }
                }catch (Exception u){
                    u.getMessage();
                }

            }
        });
    }
    public JPanel getPanel1() {
        return panel1;
    }

    public void setButtonEnable(boolean enabled) {
        selectButton.setEnabled(enabled);
        actionButton.setEnabled(enabled);

    }

    private void onFileSelect(){
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
    private void encryptFile(String password) throws Exception {
        EncrypterThread thread = new EncrypterThread(this);
        thread.setFile(selectedFile);
        thread.setPassword(password);
        thread.start();
    }

    private void decryptFile(String password) {
        DecrypterThread thread = new DecrypterThread(this);
        thread.setFile(selectedFile);
        thread.setPassword(password);
        thread.start();
    }
    void showWarning(String message){
        JOptionPane.showMessageDialog(panel1, message, "Ошибка", JOptionPane.WARNING_MESSAGE);
    }

    public void showFinished() {
        JOptionPane.showMessageDialog(
                panel1,
                encryptedFileSelected ?
                        "Расшифровка завершена" :
                        "Шифрование завершено",
                "Ошибка",
                JOptionPane.WARNING_MESSAGE);

    }


}
