package com.example.android.emojify;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

public class Emojifier {
    public static final String TAG = "Emojifier";
    /**
     * Detect faces on the image and log its count
     * */
    static void detectFaces(Context context, Bitmap bitmap) {
        FaceDetector detector = new FaceDetector.Builder(context)
                .setTrackingEnabled(false)
                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                .build();

        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
        SparseArray<Face> faces = detector.detect(frame);

        if (faces != null && faces.size() > 0) {
            Log.d(TAG, "Found " + faces.size() + " face(s)");
            getClassification(faces);
        } else {
            Toast.makeText(context, "No faces found", Toast.LENGTH_SHORT).show();
        }

        detector.release();
    }

    static void getClassification(SparseArray<Face> faces) {
        for (int faceCount =0; faceCount < faces.size(); faceCount++) {
            Face face = faces.get(faceCount);
            float isLeftEyeOpenProbability = face.getIsLeftEyeOpenProbability();
            float isRightEyeOpenProbability = face.getIsRightEyeOpenProbability();
            float isSmilingProbability = face.getIsSmilingProbability();

            Log.d(TAG, "Left eye open: " + isLeftEyeOpenProbability);
            Log.d(TAG, "Right eye open: " + isRightEyeOpenProbability);
            Log.d(TAG, "Smiling: " + isSmilingProbability);
        }
    }
}
