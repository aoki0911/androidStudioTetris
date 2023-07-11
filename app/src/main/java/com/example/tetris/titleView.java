package com.example.tetris;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class titleView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.titlelayout);


        ImageButton titleButton = findViewById(R.id.titleButton);
        titleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.startFlag = true;
                Intent intent = new Intent(titleView.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
