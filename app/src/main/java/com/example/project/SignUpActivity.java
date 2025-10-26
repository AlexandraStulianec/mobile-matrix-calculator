package com.example.project;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    EditText editTextUsername, editTextPassword;
    Button buttonSignUp;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSignUp = findViewById(R.id.buttonSignUp);

        db = openOrCreateDatabase("UserDB", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users(username VARCHAR, password VARCHAR);");

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }

    private void registerUser() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showMessage("Error", "Please enter both username and password");
            return;
        }

        if (!isPasswordValid(password)) {
            showMessage("Error", "Password must be at least 10 characters long and contain letters, numbers, and punctuation marks");
            return;
        }

        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);

        long result = db.insert("users", null, values);
        if (result != -1) {
            showMessage("Success", "User registered successfully!");
            editTextUsername.setText("");
            editTextPassword.setText("");
            openLoginActivity();
        } else {
            showMessage("Error", "Failed to register user");
        }
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    private boolean isPasswordValid(String password) {
        // Minimum 10 characters, with letters, numbers, and punctuation marks
        String pattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{10,}$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(password);
        return m.matches();
    }


    public void viewAll(View view) {
        Cursor c = db.rawQuery("SELECT * FROM users", null);
        if (c.getCount() == 0) {
            showMessage("Error", "No users found in database");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            buffer.append("Username: " + c.getString(0) + "\n");
            buffer.append("Password: " + c.getString(1) + "\n");

        }
        showMessage("User Data", buffer.toString());
    }


    public void openLoginActivity() {
        Intent intent = new Intent(this, FirstPageActivity.class);
        startActivity(intent);
    }


    public void openMainPageActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
