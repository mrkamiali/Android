package com.android.cookies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.status_text_view);
        mImageView = (ImageView) findViewById(R.id.android_cookie_image_view);

    }

    public void eatCookie(View view) {
        String newText = "I'm so FULL !";
        mTextView.setText(newText);
        mImageView.setImageResource(R.drawable.after_cookie);
        // TODO: Find a reference to the ImageView in the layout. Change the image.

    }
}
