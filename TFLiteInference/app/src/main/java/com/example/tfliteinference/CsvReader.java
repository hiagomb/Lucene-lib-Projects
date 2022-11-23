package com.example.tfliteinference;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {


    public static List<String> readCsvFile(Context context, String path){
        List<String> list = new ArrayList<>();
        try {
            InputStream inputStream = context.getAssets().open(path);
            BufferedReader br= new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while((line = br.readLine())!= null){
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
