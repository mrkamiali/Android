package com.android.cameraactivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class SnapActivity extends AppCompatActivity {
    private File imageFile;
    private Button button;
    private static final int PHOTO_TAKEN = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snap);
        button = (Button) findViewById(R.id.snap);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                File picsDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                imageFile = new File(picsDirectory, "passPoint_Image");

                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
                startActivityForResult(i, PHOTO_TAKEN);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_TAKEN){
            Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());

            if (bitmap != null){
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);
            }else {
                Toast.makeText(this,"Photo isn't save to SDCard",Toast.LENGTH_LONG).show();
            }
        }
    }
}
