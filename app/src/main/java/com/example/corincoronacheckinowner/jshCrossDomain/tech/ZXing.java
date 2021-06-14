package com.example.corincoronacheckinowner.jshCrossDomain.tech;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.nio.charset.StandardCharsets;

public class ZXing {

    public static void showQRCode(ImageView imageView, String content){
        try {
            content = new String(content.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1); // For Korean
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE,imageView.getWidth(),imageView.getHeight());
            Bitmap bitmap = new BarcodeEncoder().createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) { e.printStackTrace(); }
    }

    public static Intent getScanIntent(Activity activity){
        IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.setPrompt("");
        return intentIntegrator.createScanIntent();
    }
}
