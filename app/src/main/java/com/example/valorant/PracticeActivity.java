package com.example.valorant;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PracticeActivity extends AppCompatActivity {

    private QuadrilateralView quadrilateralView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        quadrilateralView = findViewById(R.id.quadrilateralView);
        Button btnGenerate = findViewById(R.id.btn_generate);

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quadrilateralView.redrawQuadrilateral();
            }
        });
    }
}
