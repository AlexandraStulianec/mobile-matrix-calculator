package com.example.project;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LottieAnimationView lottieAnimationView = findViewById(R.id.animation_view);
        LinearLayout mainContent = findViewById(R.id.main_content);

        // Set an animation listener to hide the animation and show main content when done
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animation) {
                // When the animation ends, hide the animation view and show the main content
                lottieAnimationView.setVisibility(View.GONE);
                mainContent.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}
        });


        TextView helloUserTextView = findViewById(R.id.textViewHelloUser);
        String username = getIntent().getStringExtra("username");
        if (username != null && !username.isEmpty()) {
            helloUserTextView.setText("Hello, " + username);
        } else {
            helloUserTextView.setText("Hello, Guest");
        }

        Button buttonLogIn = findViewById(R.id.buttonLogIn);
        buttonLogIn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FirstPageActivity.class);
            startActivity(intent);
        });

        Button buttonTheory = findViewById(R.id.buttonTheory);
        buttonTheory.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, TheoryActivity.class);
            startActivity(intent);
        });

        Button buttonExamples = findViewById(R.id.buttonExamples);
        buttonExamples.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ExamplesActivity.class);
            startActivity(intent);
        });

        Button buttonCalculator = findViewById(R.id.buttonCalculator);
        buttonCalculator.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
            startActivity(intent);
        });

        Button buttonQuiz = findViewById(R.id.buttonQuiz);
        buttonQuiz.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(intent);
        });

        Button buttonHelp = findViewById(R.id.buttonHelp);
        buttonHelp.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, HelpActivity.class);
            startActivity(intent);
        });


    }


}
