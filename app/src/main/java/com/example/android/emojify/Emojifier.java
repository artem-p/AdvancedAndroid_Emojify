package com.example.android.emojify;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

public class Emojifier {

    /**
     * Detect faces on the image and log its count
     * */
    static void detectFaces(Context context, Bitmap bitmap) {
        FaceDetector detector = new FaceDetector.Builder(context)
                .setTrackingEnabled(false)
                .build();

        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
        SparseArray<Face> faces = detector.detect(frame);

        if (faces != null && faces.size() > 0) {
            Toast.makeText(context, "Found " + faces.size() + " face(s)", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "No faces found", Toast.LENGTH_SHORT).show();
        }

    }
}
