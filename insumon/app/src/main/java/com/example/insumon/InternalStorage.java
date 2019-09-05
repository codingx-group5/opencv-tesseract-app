package com.example.insumon;

import android.content.res.AssetManager;
import android.util.Log;

import com.example.insumon.MainActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class InternalStorage {
    private AssetManager assetManager;
    private File file;

    public InternalStorage(AssetManager assetManager, File file){
        this.assetManager = assetManager;
        this.file = file;
    }

    public void checkFile(File dir) {
        if (!dir.exists()&& dir.mkdirs()){
            copyFiles();
            Log.d("dir.exists1" , String.valueOf(dir.exists()));
        }
        if(dir.exists()) {
            String datafilepath = file + "/raw/data.csv";
            File datafile = new File(datafilepath);
            Log.d("dir.exists2", String.valueOf(file + "/raw"));
            if (!datafile.exists()) {
                copyFiles();
            }
            Log.d("dir.exists3", String.valueOf(datafile.exists()));
        }
    }

    private void copyFiles() {
        try {
            String filepath = "/raw/data.csv";

            InputStream instream = assetManager.open("/raw/data.csv");
            OutputStream outstream = new FileOutputStream(filepath);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = instream.read(buffer)) != -1) {
                outstream.write(buffer, 0, read);
            }


            outstream.flush();
            outstream.close();
            instream.close();

            File file = new File(filepath);
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
