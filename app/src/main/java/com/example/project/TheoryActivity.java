package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class TheoryActivity extends AppCompatActivity {

    private Integer currentlyDisplayedFragmentLayoutResId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory);

        setupButton(R.id.buttonSum, R.layout.fragment_sum);
        setupButton(R.id.buttonSubstract, R.layout.fragment_substraction);
        setupButton(R.id.buttonMultiply, R.layout.fragment_multiplication);
        setupButton(R.id.buttonTranspose, R.layout.fragment_transposition);
        setupButton(R.id.buttonTrace, R.layout.fragment_trace);
        setupButton(R.id.buttonDeterminant, R.layout.fragment_determinant);
    }


    private void setupButton(int buttonId, @LayoutRes int layoutResId) {
        findViewById(buttonId).setOnClickListener(view -> toggleOperationFragment(layoutResId));
    }

    private void toggleOperationFragment(@LayoutRes int layoutResId) {
        if (currentlyDisplayedFragmentLayoutResId != null && currentlyDisplayedFragmentLayoutResId == layoutResId) {
            // If the fragment is already displayed, hide it
            getSupportFragmentManager().beginTransaction()
                    .remove(getSupportFragmentManager().findFragmentById(R.id.fragment_container))
                    .commit();
            currentlyDisplayedFragmentLayoutResId = null;
        } else {
            // Otherwise, replace any existing fragment with the new one
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, OperationFragment.newInstance(layoutResId))
                    .commit();
            currentlyDisplayedFragmentLayoutResId = layoutResId;
        }
    }

    public void openMainPageActivity(View view) {
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

    public void openExamplesActivity(View view) {
        Intent intent = new Intent(this, ExamplesActivity.class);
        startActivity(intent);
    }
}
