package com.example.project;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView helloUserTextView = findViewById(R.id.textViewHelloUser);

        // Check if the user is logged in
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);
        if (isLoggedIn) {
            // If logged in, display the username
            String username = prefs.getString("loggedUser", "");
            helloUserTextView.setText("Hello, " + username);
        } else {
            // If not logged in, display default greeting
            helloUserTextView.setText("Hello, Guest");
        }

        Button buttonLogIn = findViewById(R.id.buttonLogIn);
        buttonLogIn.setOnClickListener(view -> {
            Intent intent = new Intent(Main2Activity.this, FirstPageActivity.class);
            startActivity(intent);
        });

        Button buttonTheory = findViewById(R.id.buttonTheory);
        buttonTheory.setOnClickListener(view -> {
            Intent intent = new Intent(Main2Activity.this, TheoryActivity.class);
            startActivity(intent);
        });

        Button buttonExamples = findViewById(R.id.buttonExamples);
        buttonExamples.setOnClickListener(view -> {
            Intent intent = new Intent(Main2Activity.this, ExamplesActivity.class);
            startActivity(intent);
        });

        Button buttonCalculator = findViewById(R.id.buttonCalculator);
        buttonCalculator.setOnClickListener(view -> {
            Intent intent = new Intent(Main2Activity.this, Calculator2Activity.class);
            startActivity(intent);
        });

        Button buttonQuiz = findViewById(R.id.buttonQuiz);
        buttonQuiz.setOnClickListener(view -> {
            Intent intent = new Intent(Main2Activity.this, QuizActivity.class);
            startActivity(intent);
        });

        Button buttonHelp = findViewById(R.id.buttonHelp);
        buttonHelp.setOnClickListener(view -> {
            Intent intent = new Intent(Main2Activity.this, HelpActivity.class);
            startActivity(intent);
        });

        Button buttonLogOut = findViewById(R.id.buttonLogOut);
        buttonLogOut.setOnClickListener(this::logOutUser);
    }

    // Method to handle logout
    public void logOutUser(View view) {
        // Clear all shared preferences related to the user session
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear(); // Remove all data from SharedPreferences
        editor.apply();

        // Start MainActivity with a clear top flag to ensure no back stack with logged-in state
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
