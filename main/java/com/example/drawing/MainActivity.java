package com.example.drawing;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.UUID;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity{

    CanvasView canvas;
    int mydefaultcolor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        canvas = (CanvasView) findViewById(R.id.mycanvas);
        FloatingActionButton flbtn = findViewById(R.id.ftb1);
        FloatingActionButton colorflb = findViewById(R.id.colorftb);
        FloatingActionButton saveflb = findViewById(R.id.saveftb);
        mydefaultcolor = ContextCompat.getColor(MainActivity.this,R.color.colorPrimary);
        saveflb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveimage();
            }
        });
        colorflb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencolorpiker();
            }
        });
        flbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.clearcanvas();
            }
        });
    }

    private void saveimage() {
        AlertDialog.Builder savedia = new AlertDialog.Builder(this);
        savedia.setTitle("save?");
        savedia.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                canvas.setDrawingCacheEnabled(true);
                String imgsave = MediaStore.Images.Media.insertImage(
                        getContentResolver(),canvas.getDrawingCache(),
                        UUID.randomUUID().toString()+".jpg","drawing");
                if (imgsave!=null){
                    Toast savedtoast = Toast.makeText(getApplicationContext(),"saved",Toast.LENGTH_LONG);
                    savedtoast.show();
                }
                else {
                    Toast unsavedtoast = Toast.makeText(getApplicationContext(),"unsaved",Toast.LENGTH_LONG);
                    unsavedtoast.show();
                }
                canvas.destroyDrawingCache();
            }
        });
        savedia.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        savedia.show();
    }

    private void opencolorpiker() {
        AmbilWarnaDialog colorpiker = new AmbilWarnaDialog(this, mydefaultcolor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                Toast.makeText(MainActivity.this, "cancle", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                    mydefaultcolor = color;
                    canvas.setBackgroundColor(mydefaultcolor);
            }
        });
        colorpiker.show();
    }
}
