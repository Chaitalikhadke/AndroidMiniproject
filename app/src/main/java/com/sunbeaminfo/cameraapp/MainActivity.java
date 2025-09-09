package com.sunbeaminfo.cameraapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;
import com.sunbeaminfo.cameraapp.R;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private ImageCapture imageCapture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreviewView previewView = findViewById(R.id.previewView);
        Button captureButton = findViewById(R.id.captureButton);

        ListenableFuture<ProcessCameraProvider> cameraProviderFuture =
                ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                Preview preview = new Preview.Builder().build();
                preview.setSurfaceProvider(previewView.getSurfaceProvider());

                imageCapture = new ImageCapture.Builder()
                        .setTargetRotation(previewView.getDisplay().getRotation())
                        .build();

                CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;
                cameraProvider.unbindAll();
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(this));

        captureButton.setOnClickListener(v -> {
            if (imageCapture == null) return;
            File photoFile = new File(getExternalMediaDirs()[0],
                    "IMG_" + System.currentTimeMillis() + ".jpg");

            ImageCapture.OutputFileOptions outputOptions =
                    new ImageCapture.OutputFileOptions.Builder(photoFile).build();

            imageCapture.takePicture(outputOptions, ContextCompat.getMainExecutor(this),
                    new ImageCapture.OnImageSavedCallback() {
                        @Override
                        public void onImageSaved(ImageCapture.OutputFileResults output) {
                            // Optionally, update gallery and notify MediaStore
                        }

                        @Override
                        public void onError(ImageCaptureException exception) {
                            exception.printStackTrace();
                        }
                    });
        });
    }
}
