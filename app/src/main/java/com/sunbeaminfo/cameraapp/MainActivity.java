package com.sunbeaminfo.cameraapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button click,rear,front;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        click=findViewById(R.id.click);
        rear=findViewById(R.id.rear);
        front=findViewById(R.id.front);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
  public void click(View view) {
       Intent intent = new Intent();
       intent.setAction(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
       startActivity(intent);
  }
  public void front(View view) {
        Intent intent = new Intent();
        intent.setAction(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
  }
  public void rear(View view) {
        Intent intent = new Intent();
        intent.setAction(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
  }

    @Override
    public Intent getIntent() {
        intent.putExtra(this.getString(R.string.app_name),this.getString(R.string.app_name));
        return intent;
    }
}