package com.example.demo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class MainActivity2 extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String valor = getIntent().getExtras().getString("token");
        BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
        ImageView imageView=(ImageView) findViewById(R.id.IMAGENQR) ;
        TextView textView=(TextView) findViewById(R.id.textView4);
        Bitmap bitmap= null;
        try {
            bitmap = barcodeEncoder.encodeBitmap(valor.toString(), BarcodeFormat.QR_CODE,750,750);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(bitmap);
        textView.setText(valor);


    }

}