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
public class MainActivity extends AppCompatActivity {
    boolean isXTurn = true;
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

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView clickedCell = (TextView) v;
                int tappedCell = Integer.parseInt(clickedCell.getTag().toString());
                if (clickedCell.getText().toString().isEmpty()) {

                    if (isXTurn) {
                        clickedCell.setText("X");
                        clickedCell.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                    } else {
                        clickedCell.setText("O");
                        clickedCell.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                    }

                    clickedCell.setTextSize(120);

                    String player = isXTurn ? "X" : "O";

                    Toast.makeText(MainActivity.this,
                            "Cell " + tappedCell + " clicked - " + player,
                            Toast.LENGTH_SHORT).show();

                    clickedCell.setTranslationY(-clickedCell.getHeight() * 3);
                    clickedCell.animate()
                            .translationY(0f)
                            .setDuration(3000)
                            .setInterpolator(new android.view.animation.BounceInterpolator())
                            .start();

                    isXTurn = !isXTurn;
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
}