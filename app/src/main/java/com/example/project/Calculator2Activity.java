package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Calculator2Activity extends AppCompatActivity {

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
            setContentView(R.layout.activity_calculator_2_3x3);
        } else if (size == 4) {
            setContentView(R.layout.activity_calculator_2_4x4);
        } else if (size == 2) {
            setContentView(R.layout.activity_calculator_2_2x2);
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
        if (currentMatrixSize < 5) { // Assuming 4x4 is the maximum matrix size
            currentMatrixSize++;
            setMatrixLayout(currentMatrixSize);
        }
    }

    public void decreaseMatrixSize(View view) {
        if (currentMatrixSize > 2) { // Assuming 2x2 is the minimum matrix size
            currentMatrixSize--;
            setMatrixLayout(currentMatrixSize);
        }
    }

    public void openQuizActivity(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }


    public void calculateTransposeA(View view) {
        int[][] transposedMatrix = calculateTranspose(matrixA);
        displayResult(transposedMatrix);
    }

    public void calculateTransposeB(View view) {
        int[][] transposedMatrix = calculateTranspose(matrixB);
        displayResult(transposedMatrix);
    }

    private int[][] calculateTranspose(EditText[][] matrix) {
        int size = matrix.length;
        int[][] transposedMatrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                transposedMatrix[j][i] = Integer.parseInt(matrix[i][j].getText().toString());
            }
        }
        return transposedMatrix;
    }


    public void calculateTraceA(View view) {
        int trace = calculateTrace(matrixA);
        displayTrace(trace);
    }

    public void calculateTraceB(View view) {
        int trace = calculateTrace(matrixB);
        displayTrace(trace);
    }

    private int calculateTrace(EditText[][] matrix) {
        int size = matrix.length;
        int trace = 0;
        for (int i = 0; i < size; i++) {
            trace += Integer.parseInt(matrix[i][i].getText().toString());
        }
        return trace;
    }

    private void displayTrace(int trace) {
        TextView traceView = findViewById(R.id.tvTrace);
        traceView.setText("Trace: " + trace);
    }

    public void calculateDeterminantA(View view) {
        int det = calculateDeterminant(matrixA);
        displayDeterminant(det);
    }

    public void calculateDeterminantB(View view) {
        int det = calculateDeterminant(matrixB);
        displayDeterminant(det);
    }

    private void displayDeterminant(int determinant) {
        TextView tvDeterminant = findViewById(R.id.tvTrace);
        tvDeterminant.setText("Determinant: " + determinant);
    }


    private int calculateDeterminant(EditText[][] matrix) {
        int size = matrix.length;
        int[][] intMatrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                intMatrix[i][j] = Integer.parseInt(matrix[i][j].getText().toString());
            }
        }

        return determinant(intMatrix);
    }

    private int determinant(int[][] matrix) {
        int size = matrix.length;
        if (size == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        if (size == 3) {
            // Rule of Sarrus for 3x3 matrices
            return matrix[0][0] * (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1])
                    + matrix[0][1] * (matrix[1][2] * matrix[2][0] - matrix[1][0] * matrix[2][2])
                    + matrix[0][2] * (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]);
        }
        // Laplace expansion for 4x4 or higher (if needed)
        int determinant = 0;
        for (int i = 0; i < size; i++) {
            int[][] minor = getMinor(matrix, 0, i);
            determinant += Math.pow(-1, i) * matrix[0][i] * determinant(minor);
        }
        return determinant;
    }

    private int[][] getMinor(int[][] matrix, int row, int column) {
        int minorSize = matrix.length - 1;
        int[][] minor = new int[minorSize][minorSize];
        int dRow = 0;
        for (int i = 0; i <= minorSize; i++) {
            if (i == row) continue;
            int dCol = 0;
            for (int j = 0; j <= minorSize; j++) {
                if (j == column) continue;
                minor[dRow][dCol] = matrix[i][j];
                dCol++;
            }
            dRow++;
        }
        return minor;
    }

    public void openMain2Activity(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
