package com.khaledodat.assessment.data.local.files;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.khaledodat.assessment.view.callbacks.ResponseListener;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class AssetFiles {
    Context context;
    Gson gson;

    public AssetFiles(Context context, Gson gson) {
        this.context = context;
        this.gson = gson;
    }

    private String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
