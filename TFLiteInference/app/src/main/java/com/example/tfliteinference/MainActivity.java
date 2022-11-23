package com.example.tfliteinference;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.tensorflow.lite.Interpreter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String CSV_PATH = "data_inference.csv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get list from .csv
        List<String> list = CsvReader.readCsvFile(this, CSV_PATH);

        //run model
        Interpreter interpreter = PhoneticPredictor.getInstance(this);
        PhoneticPredictor phoneticPredictor = new PhoneticPredictor();
        for(String l : list){
            phoneticPredictor.runModel(interpreter, l);
        }
    }



}