package com.example.project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.MediaController;


import androidx.appcompat.app.AppCompatActivity;

public class HelpActivity extends AppCompatActivity {

    private VideoView videoView;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        addDynamicContent();
        setupVideoView();

    }

    private void setupVideoView() {
        videoView = findViewById(R.id.videoView);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.example_help;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        // Set up MediaController
        if (mediaController == null) {
            mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
        }

        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    videoView.pause();
                } else {
                    videoView.start();
                }
            }
        });

        videoView.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (videoView != null && videoView.isPlaying()) {
            videoView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (videoView != null) {
            videoView.start();
        }
    }

    private void addDynamicContent() {
        LinearLayout layout = findViewById(R.id.layoutHelpContent);

        // Add dynamic link
        TextView dynamicLinkTextView = new TextView(this);
        String linkText = "For more matrix operations, visit GeeksForGeeks or Matrix Calculator.";
        SpannableString spannableLinkText = new SpannableString(linkText);

        // GeeksForGeeks Link
        ClickableSpan gfgLink = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.geeksforgeeks.org/matrices/")));
            }
        };

        // Matrix Calculator Link
        ClickableSpan matrixCalcLink = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://matrixcalc.org/")));
            }
        };

        // Set spans for links
        spannableLinkText.setSpan(gfgLink, 34, 46, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableLinkText.setSpan(matrixCalcLink, 51, 67, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        dynamicLinkTextView.setText(spannableLinkText);
        dynamicLinkTextView.setMovementMethod(LinkMovementMethod.getInstance());
        layout.addView(dynamicLinkTextView);
    }


    public void openMainActivity(View view) {
        if (!FirstPageActivity.isLoggedIn(this)) {
            Intent intent = new Intent(this, MainActivity.class);
            // Intent.FLAG_ACTIVITY_CLEAR_TOP clears the current task and starts a new task with MainActivity
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, Main2Activity.class);
            // Intent.FLAG_ACTIVITY_CLEAR_TOP clears the current task and starts a new task with MainActivity
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }

    }
}
