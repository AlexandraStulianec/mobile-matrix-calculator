package com.example.project;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

public class ExamplesActivity extends AppCompatActivity {

    private Integer currentlyDisplayedFragmentLayoutResId = null;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examples);
        db = openOrCreateDatabase("UserDB", MODE_PRIVATE, null);

        setupButtons();


    }

    private void setupButtons() {
        setupButton(R.id.buttonSum, R.layout.examples_sum);
        setupButton(R.id.buttonSubstract, R.layout.examples_substraction);
        setupButton(R.id.buttonMultiply, R.layout.examples_multiplication);
        setupButton(R.id.buttonTranspose, R.layout.examples_transposition);
        setupButton(R.id.buttonTrace, R.layout.examples_trace);
        setupButton(R.id.buttonDeterminant, R.layout.examples_determinant);
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

    public void openCalculatorActivity(View view) {
        if (!FirstPageActivity.isLoggedIn(this)) {
            Intent intent = new Intent(this, CalculatorActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, Calculator2Activity.class);
            startActivity(intent);
        }
    }
}
