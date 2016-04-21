package com.android.image_passwordapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import static com.android.image_passwordapp.MainActivity.*;

public class Image_Activity extends AppCompatActivity implements PointCollector {
    private static final String SET_PASSWORD = "SET_PASSWORD";
    private static final int POINT_CLOSSNESS = 40;
    private static final int PHOTO_TAKEN = 0;
    private Button button;
    private File imageFile;

    private ImageView imageView;
    private PointListener pointListener = new PointListener();
    private DataBase dataBase = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_);
        button = (Button) findViewById(R.id.snapPic);

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

        imageView = (ImageView) findViewById(R.id.touch_Screen);
        imageView.setOnTouchListener(pointListener);
        pointListener.setPointCollector(this);

        Bundle extras = getIntent().getExtras();

        if (extras != null){
            Boolean resetPassPoints = extras.getBoolean(MainActivity.RESET_PASSWORD);
            if (resetPassPoints){

            }
        }

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        Boolean setPassPoint = preferences.getBoolean(SET_PASSWORD, false);

        if (!setPassPoint) {
            showPrompt();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_TAKEN){
            Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());

            if (bitmap != null){
                imageView.setImageBitmap(bitmap);
            }else {
                Toast.makeText(this,"Photo isn't save to SDCard",Toast.LENGTH_LONG).show();
            }
        }
    }

    private void showPrompt() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please Select your newPassword.");
        builder.setMessage("Select any Four Points to set your Password.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create().show();
    }

    private void savePoints(final List<Point> points) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("StoringPoints ...");
        final AlertDialog dialog = builder.create();
        dialog.show();
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                dataBase.storePoints(points);
                Log.d("TAG", "PointSaved : " + points.size());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                SharedPreferences preferences = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(SET_PASSWORD, true);
                editor.commit();

                pointListener.clear();
                dialog.dismiss();
            }
        };
        task.execute();
    }

    private void verifyPoints(final List<Point> touchedpoints) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("VerifyingPassPoints...");
        final AlertDialog dialog = builder.create();
        dialog.show();
        AsyncTask<Void, Void, Boolean> task = new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<Point> savedPoints = dataBase.getPoints();
                Log.d("TAG", "Loaded Points" + savedPoints.size());

                if (savedPoints.size() != pointListener.Num_Points || touchedpoints.size() != pointListener.Num_Points) {
                    return false;
                }
                for (int i = 0; i < pointListener.Num_Points; i++) {
                    Point savedPoint = savedPoints.get(i);
                    Point touchedPoint = touchedpoints.get(i);
                    //Calculating Difference b/w saved and touch points;
                    int xdiff = savedPoint.x - touchedPoint.x;
                    int ydiff = savedPoint.y - touchedPoint.y;
                    //squarring differnece b/w points;
                    int distanceSquared = xdiff * xdiff + ydiff * ydiff;

                    if (distanceSquared > POINT_CLOSSNESS*POINT_CLOSSNESS){
                        return false;
                    }


                }

                return true;
            }

            @Override
            protected void onPostExecute(Boolean pass) {
                if (pass == true) {
                    Intent i = new Intent(Image_Activity.this, MainActivity.class);
                    startActivity(i);
                    Toast.makeText(Image_Activity.this,"UnLocked..!",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Image_Activity.this, "AccessDenied", Toast.LENGTH_SHORT).show();
                    Toast.makeText(Image_Activity.this, "WrongPass..!", Toast.LENGTH_LONG).show();

                }
                dialog.dismiss();
                pointListener.clear();
            }
        };
        task.execute();
    }

    @Override
    public void listener(final List<Point> points) {

        Log.d("TAG", "Collected Point SIze : " + points.size());
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        Boolean setPassPoint = preferences.getBoolean(SET_PASSWORD, false);

        if (setPassPoint == false) {
            Log.d("TAG", "SavingPassPoints ...");
            savePoints(points);
        } else {
            Log.d("TAG", "VerifyingPassPoints ...");
            verifyPoints(points);
        }


//        {   //GETTING POINTS FROM DATA BASE:
//            List<Point> points1 = dataBase.getPoints();
//            for (Point point : points) {
//                Log.d("TAG", String.format("StoredPoints: %d , %d !", point.x, point.y));
//            }
//        }
    }
}
