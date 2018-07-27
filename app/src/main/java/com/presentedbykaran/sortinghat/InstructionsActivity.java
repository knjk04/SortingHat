package com.presentedbykaran.sortinghat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InstructionsActivity extends AppCompatActivity {

    Button button;
    TextView txtName;
    private static final String TAG = InstructionsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        Intent intent = getIntent();
        String[] fullName = intent.getStringArrayExtra("fullName");
        txtName = findViewById(R.id.txtFullName);
        splitName(fullName);

        button = findViewById(R.id.btnSort);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InstructionsActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });
    }

    private void splitName(String[] fullName) {
        String name = fullName[1] + ", " + fullName[0];
        txtName.setText(name);
    }
}
