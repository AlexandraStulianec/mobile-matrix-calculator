package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private RadioGroup group1, group2, group3, group4, group5;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialize views
        group1 = findViewById(R.id.answers_group1);
        group2 = findViewById(R.id.answers_group2);
        group3 = findViewById(R.id.answers_group3);
        group4 = findViewById(R.id.answers_group4);
        group5 = findViewById(R.id.answers_group5);
        submitButton = findViewById(R.id.submit_button);

        // Setup the submit button
        submitButton.setOnClickListener(view -> checkAnswers());
    }

    private void checkAnswers() {
        // Disable submit button to prevent multiple submissions
        submitButton.setEnabled(false);

        // Animate the screen to fade out
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        final View quizLayout = findViewById(R.id.quiz_layout);
        quizLayout.startAnimation(fadeOut);

        new Handler().postDelayed(() -> {
            quizLayout.setVisibility(View.INVISIBLE);
            revealCorrectAnswers();
        }, 500); // Wait for fade out to complete
    }

    private void revealCorrectAnswers() {
        checkAnswer(group1, R.id.answer1_1, "Question 1");
        checkAnswer(group2, R.id.answer2_1, "Question 2");
        checkAnswer(group3, R.id.answer3_2, "Question 3");
        checkAnswer(group4, R.id.answer4_1, "Question 4");
        checkAnswer(group5, R.id.answer5_2, "Question 5");

        // Delay after revealing correct answers
        new Handler().postDelayed(() -> {
            final View quizLayout = findViewById(R.id.quiz_layout);
            // Start fade in animation
            Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
            quizLayout.startAnimation(fadeIn);
            quizLayout.setVisibility(View.VISIBLE);

            // Resetting quiz for potential re-use
            resetQuiz();
            submitButton.setEnabled(true); // Re-enable submit button
        }, 500); // Delay before fading in and resetting
    }

    private void resetQuiz() {
        group1.clearCheck();
        group2.clearCheck();
        group3.clearCheck();
        group4.clearCheck();
        group5.clearCheck();
    }

    private void checkAnswer(RadioGroup group, int correctId, String question) {
        int selectedId = group.getCheckedRadioButtonId();
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, null);

        TextView text = layout.findViewById(R.id.custom_toast_text);

        if (selectedId != correctId) {
            // Incorrect answer
            text.setText("Review the answer for " + question);
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();
        } else {
            // Correct answer
            RadioButton correctAnswer = findViewById(correctId);
            text.setText("Correct answer for " + question + ": " + correctAnswer.getText());
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
        }
    }


    public void openHelpActivity(View view) {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

    public void openMainActivity(View view) {
        if (!FirstPageActivity.isLoggedIn(this)) {
            Intent intent = new Intent(this, MainActivity.class);
            // Intent.FLAG_ACTIVITY_CLEAR_TOP clears the current task and starts a new task with MainActivity
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, Main2Activity.class);
            // Intent.FLAG_ACTIVITY_CLEAR_TOP clears the current task and starts a new task with MainActivity
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }
    }
}
