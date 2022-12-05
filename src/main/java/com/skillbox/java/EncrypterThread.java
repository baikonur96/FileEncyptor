package com.skillbox.java;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;

import javax.security.auth.PrivateCredentialPermission;
import javax.security.auth.login.Configuration;
import java.io.File;
import java.lang.reflect.Parameter;

public class EncrypterThread extends Thread {

    private  GUIForme form;
    private File file;
    private ZipParameters parameters;

    public EncrypterThread(GUIForme form){
        this.form = form;
    }

    public void setFile(File file){
        this.file = file;
    }

    public void setPassword(String password) throws Exception{
        parameters = ParametersContainer.getParameters();
        parameters.setPassword(password);
    }


    @Override
    public void run() {
        onStart();
        try {
            String archiveName = getArchiveName();
            ZipFile zipFile = new ZipFile(archiveName);
            if (file.isDirectory()){
                zipFile.addFolder(file, parameters);
            }
        } catch (Exception ex) {
            form.showWarning(ex.getMessage());
        }
        onFinish();
    }

    private void onStart(){
        form.setButtonEnable(false);
    }

    private void onFinish(){
        form.setButtonEnable(true);
        parameters.setPassword("");
        form.showFinished();
    }

    private String getArchiveName() {
        for (int i = 1; ; i++){
            String number  = i > 0 ? Integer.toString(i) : "";
            String archiveName = file.getAbsolutePath() + number + ".zip";
            if (!new File(archiveName).exists()) {
                return archiveName;
            }
        }
    }

}
