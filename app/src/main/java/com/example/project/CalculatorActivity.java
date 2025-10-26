package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    private EditText[][] matrixA;
    private EditText[][] matrixB;
    private TextView[][] resultMatrix;
    private int currentMatrixSize = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMatrixLayout(currentMatrixSize);

    }

    private void setMatrixLayout(int size) {
        if (size == 3) {
            setContentView(R.layout.activity_calculator_3x3);
        } else if (size == 4) {
            setContentView(R.layout.activity_calculator_4x4);
        } else if (size == 2) {
            setContentView(R.layout.activity_calculator_2x2);
        }
        initializeMatrixViews(size);
    }

    private void initializeMatrixViews(int size) {
        matrixA = new EditText[size][size];
        matrixB = new EditText[size][size];
        resultMatrix = new TextView[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrixA[i][j] = findViewById(getResources().getIdentifier("A" + (i + 1) + (j + 1), "id", getPackageName()));
                matrixB[i][j] = findViewById(getResources().getIdentifier("B" + (i + 1) + (j + 1), "id", getPackageName()));
                resultMatrix[i][j] = findViewById(getResources().getIdentifier("R" + (i + 1) + (j + 1), "id", getPackageName()));
            }
        }
    }

    public void multiplyMatrixA(View view) {
        multiplyMatrix(matrixA);
    }

    public void multiplyMatrixB(View view) {
        multiplyMatrix(matrixB);
    }

    private void multiplyMatrix(EditText[][] matrix) {
        EditText scalarInput;
        int[][] result = new int[currentMatrixSize][currentMatrixSize];
        if (matrix == matrixA) {
            scalarInput = findViewById(R.id.editTextScalarA);
        } else {
            scalarInput = findViewById(R.id.editTextScalarB);
        }

        int scalar;
        try {
            scalar = Integer.parseInt(scalarInput.getText().toString());
        } catch (NumberFormatException e) {
            scalarInput.setError("Enter a valid number");
            return;
        }

        for (int i = 0; i < currentMatrixSize; i++) {
            for (int j = 0; j < currentMatrixSize; j++) {
                int value = matrix[i][j].getText().toString().isEmpty() ? 0 : Integer.parseInt(matrix[i][j].getText().toString());
                result[i][j] = value * scalar;
            }
        }
        displayResult(result);
    }

    public void performAddition(View view) {
        performOperation(matrixA, matrixB, '+');
    }

    public void performSubtraction(View view) {
        performOperation(matrixA, matrixB, '-');
    }

    public void performMultiplication(View view) {
        performOperation(matrixA, matrixB, '*');
    }

    private void performOperation(EditText[][] matrixA, EditText[][] matrixB, char operation) {
        int size = matrixA.length;
        int[][] result = new int[size][size];

        try {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    int a = Integer.parseInt(matrixA[i][j].getText().toString());
                    int b = Integer.parseInt(matrixB[i][j].getText().toString());

                    switch (operation) {
                        case '+':
                            result[i][j] = a + b;
                            break;
                        case '-':
                            result[i][j] = a - b;
                            break;
                        case '*':
                            result[i][j] = 0; // Placeholder for actual matrix multiplication logic
                            for (int k = 0; k < size; k++) {
                                int valueA = matrixA[i][k].getText().toString().isEmpty() ? 0 : Integer.parseInt(matrixA[i][k].getText().toString());
                                int valueB = matrixB[k][j].getText().toString().isEmpty() ? 0 : Integer.parseInt(matrixB[k][j].getText().toString());
                                result[i][j] += valueA * valueB;
                            }
                            break;
                    }
                }
            }
            displayResult(result);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void displayResult(int[][] result) {
        int size = result.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                resultMatrix[i][j].setText(String.valueOf(result[i][j]));
            }
        }
    }

    public void cleanMatrices(View view) {
        cleanMatrix(matrixA);
        cleanMatrix(matrixB);
    }

    private void cleanMatrix(EditText[][] matrix) {
        for (EditText[] row : matrix) {
            for (EditText editText : row) {
                editText.setText("");
            }
        }
    }


    public void increaseMatrixSize(View view) {
        if (currentMatrixSize < 5) {
            currentMatrixSize++;
            setMatrixLayout(currentMatrixSize);
        }
    }

    public void decreaseMatrixSize(View view) {
        if (currentMatrixSize > 2) {
            currentMatrixSize--;
            setMatrixLayout(currentMatrixSize);
        }
    }

    public void openQuizActivity(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
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
