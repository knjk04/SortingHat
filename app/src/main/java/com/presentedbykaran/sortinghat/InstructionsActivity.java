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
        String name = intent.getStringExtra("name");
        Log.d(TAG, "name: " + name);
        txtName = findViewById(R.id.txtFullName);
//        txtName.setText(name);
        splitName(name);

        button = findViewById(R.id.btnSort);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InstructionsActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });
    }

    // Only works if the witch/wizard's first name is one word and their second name is one word
    // Would be better to request the first and second name separately to handle first names that
    // are 2 or more words long
    private void splitName(String name) {
        String[] split = name.split(" ");
//        for (String s : split) Log.d(TAG, "split: " + s);
        String formattedName = split[1] + ", " + split[0];
        txtName.setText(formattedName);
    }
}
