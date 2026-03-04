package com.gir.gridxo_app;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Toast;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
    boolean isXTurn = true;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    boolean gameActive = true;
    int[][] winningPositions = {
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {2,4,6}
    };

    Button restartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView cell1 = findViewById(R.id.cell1);
        TextView cell2 = findViewById(R.id.cell2);
        TextView cell3 = findViewById(R.id.cell3);
        TextView cell4 = findViewById(R.id.cell4);
        TextView cell5 = findViewById(R.id.cell5);
        TextView cell6 = findViewById(R.id.cell6);
        TextView cell7 = findViewById(R.id.cell7);
        TextView cell8 = findViewById(R.id.cell8);
        TextView cell9 = findViewById(R.id.cell9);

        restartButton = findViewById(R.id.restartButton);

        restartButton.setOnClickListener(v -> restartGame());

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView clickedCell = (TextView) v;
                int tappedCell = Integer.parseInt(clickedCell.getTag().toString());
                if (gameState[tappedCell] == 2 && gameActive) {

                    if (isXTurn) {
                        clickedCell.setText("X");
                        clickedCell.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                        gameState[tappedCell] = 0;
                    } else {
                        clickedCell.setText("O");
                        clickedCell.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                        gameState[tappedCell] = 1;
                    }

                    clickedCell.setTextSize(80);

                    for (int[] winningPosition : winningPositions) {

                        if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                                gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                                gameState[winningPosition[0]] != 2) {

                            String winner = gameState[winningPosition[0]] == 0 ? "X" : "O";

                            Toast.makeText(MainActivity.this,
                                    winner + " is the Winner!",
                                    Toast.LENGTH_LONG).show();
                            gameActive = false;
                            restartButton.setVisibility(View.VISIBLE);
                        }
                    }

                    clickedCell.setTranslationY(-clickedCell.getHeight() * 3);
                    clickedCell.animate()
                            .translationY(0f)
                            .setDuration(3000)
                            .setInterpolator(new android.view.animation.BounceInterpolator())
                            .start();

                    isXTurn = !isXTurn;

                    boolean flag = false;
                    for (int i = 0; i < gameState.length; i++) {
                        if (gameState[i] == 2) {
                            flag = true;
                            break;
                        }
                    }

                    if(!flag) {
                        Toast.makeText(MainActivity.this,
                                "Game Over!",
                                Toast.LENGTH_LONG).show();
                        gameActive = false;
                        restartButton.setVisibility(View.VISIBLE);
                    }
                }
            }
        };

        cell1.setOnClickListener(listener);
        cell2.setOnClickListener(listener);
        cell3.setOnClickListener(listener);
        cell4.setOnClickListener(listener);
        cell5.setOnClickListener(listener);
        cell6.setOnClickListener(listener);
        cell7.setOnClickListener(listener);
        cell8.setOnClickListener(listener);
        cell9.setOnClickListener(listener);
    }

    public void restartGame() {

        GridLayout grid = findViewById(R.id.gameGrid);

        for (int i = 0; i < grid.getChildCount(); i++) {
            TextView cell = (TextView) grid.getChildAt(i);
            cell.setText("");
        }

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        isXTurn = true;
        gameActive = true;

        restartButton.setVisibility(View.GONE);
    }
}