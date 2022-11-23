package com.example.tfliteinference;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.InterpreterFactory;
import org.tensorflow.lite.Tensor;
import org.tensorflow.lite.TensorFlowLite;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneticPredictor {

    private static final String TAG = "PhoneticsPredictor";
    private static Interpreter mInterpreter = null;
    private static final String PHOENETIC_PREDICTION_PT_BR_MODEL = "g2p_english_accu_no_stress_" +
            "fixed_var_improve_beam_new_out_android.tflite";





    public PhoneticPredictor(){
    }

    public static synchronized Interpreter getInstance(Context context) {
        if(mInterpreter == null){
            try {
                Interpreter.Options options = new Interpreter.Options();
                mInterpreter = new Interpreter(loadModelFile(context.getAssets()), options);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mInterpreter;
    }




    private static MappedByteBuffer loadModelFile(AssetManager assetManager) throws IOException {
        AssetFileDescriptor modelFileDescriptor = assetManager.
                openFd(PHOENETIC_PREDICTION_PT_BR_MODEL);
        FileInputStream modelInput = new FileInputStream(modelFileDescriptor.getFileDescriptor());
        FileChannel modelFileChannel = modelInput.getChannel();
        Long fileStartOffset = modelFileDescriptor.getStartOffset();
        Long declaredLenght = modelFileDescriptor.getDeclaredLength();
//        MappedByteBuffer mappedByteBuffer = modelFileChannel.map(FileChannel.MapMode.READ_ONLY,
//        fileStartOffset, declaredLenght);
//        mappedByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
//        return mappedByteBuffer;
        return  modelFileChannel.map(FileChannel.MapMode.READ_ONLY, fileStartOffset, declaredLenght);
    }




    public String[] runModel(Interpreter interpreter, String input) {

//        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(20);
//        byteBuffer.order(ByteOrder.nativeOrder());
//        byteBuffer.put(input.getBytes(StandardCharsets.UTF_8));
//        String teste = new String(byteBuffer.array());


        Log.d("LEVI", "name to be found: " + (input));
        Map<String, Object> inputs = new HashMap<>();


        String[] inp = {input};



        inputs.put("input_text", inp);
        Map<String, Object> outputs = new HashMap<>();



        float[][] backward = new float[1][256];
        float[][] enc_add = new float[1][256];
        float[][][] enc_embedding = new float[1][30][64];
        float[][][] enc_output = new float[1][5][256];
        long[][] enc_tokens = new long[1][5];
        float[][][] enc_vectors = new float[1][5][64];
        float[][] end_forward = new float[1][256];
        String[][] result = { {""}, {""}, {""} };


//        outputs.put("backward", backward);
//        outputs.put("enc_add", enc_add);
//        outputs.put("enc_embedding", enc_embedding);
//        outputs.put("enc_output", enc_output);
        outputs.put("enc_tokens", enc_tokens);
//        outputs.put("enc_vectors", enc_vectors);
//        outputs.put("end_forward", end_forward);
        outputs.put("text", result);



        long start1 = System.currentTimeMillis();
        interpreter.runSignature(inputs, outputs, "serving_default");
        long end1 = System.currentTimeMillis();


        Long duration = end1 - start1;

        Log.d("LEVI", "backward: "+Arrays.deepToString(backward));
        Log.d("LEVI", "enc_add: "+Arrays.deepToString(enc_add));
        Log.d("LEVI", "enc_embedding: "+Arrays.deepToString(enc_embedding));
        Log.d("LEVI", "enc_output: "+Arrays.deepToString(enc_output));
        Log.d("LEVI", "enc_tokens: "+Arrays.deepToString(enc_tokens));
        Log.d("LEVI", "enc_vectors: "+Arrays.deepToString(enc_vectors));
        Log.d("LEVI", "end_forward: "+Arrays.deepToString(end_forward));
        Log.d("LEVI", "result is: " + Arrays.deepToString(result));


//        count += 1;
//        total_elapsed_time += duration;


        //teste
        String[] res = {"", "", ""};
        res[0] = result[0][0].trim();
        res[1] = result[1][0].trim();
        res[2] = result[2][0].trim();
        return res;
    }

}
