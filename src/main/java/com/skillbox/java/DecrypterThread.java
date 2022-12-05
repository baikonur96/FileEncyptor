package com.skillbox.java;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;

public class DecrypterThread  extends Thread{
    private GUIForme form;
    private File file;

    private String password;

    public DecrypterThread(GUIForme form)
    {
        this.form = form;
    }

    public void setFile(File file){
        this.file = file;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public void run() {
        onStart();
        try {
            String outPath = getOutputPath();
            ZipFile zipFile = new ZipFile(file);
            zipFile.setPassword(password);
            zipFile.extractAll(outPath);
        } catch (ZipException e) {
           form.showWarning(e.getMessage());
        }
        onFinish();
    }

    private String getOutputPath() {
        String path = file.getAbsolutePath().replaceAll("\\.zip$", "");
        for (int i = 1; ; i++) {
            String number = i > 1 ? Integer.toString(i) : "";
            String outPath = path + number;
            if (new File(outPath).exists()) {
                return path;
            }
        }

    }

    private void onStart(){
        form.setButtonEnable(false);
    }

    private void onFinish(){
        form.setButtonEnable(true);
        form.showFinished();
    }
}
