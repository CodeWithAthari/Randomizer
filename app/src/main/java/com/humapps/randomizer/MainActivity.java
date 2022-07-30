package com.humapps.randomizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String text = "Apples,Apricots,Bananas,Bing Chery,Fig, Mango";
    String[] arrStr;
    EditText editText = null;
    TextView textView = null;
    Button button = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            editText = findViewById(R.id.edittext);
            textView = findViewById(R.id.textView);
            button = findViewById(R.id.button);
            button.setOnLongClickListener(view -> {
                editText.setText(text);
                return true;
            });
            textView.setVisibility(View.INVISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        button.setOnClickListener(view -> {
            try {
                text = editText.getText().toString().trim();
                arrStr = text.split(",");

                if (arrStr.length > 2) {
                    textView.setVisibility(View.VISIBLE);
                    String randomValue = randomValue(arrStr);
                    Toast.makeText(getApplicationContext(), "Random Text is: " + randomValue, Toast.LENGTH_SHORT).show();
                    textView.setText("Random Text is: " + randomValue);
                    Log.d("Arr", randomValue(arrStr));

                } else {
                    Toast.makeText(this, "Enter At least 2 values", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "We have an error: " + e, Toast.LENGTH_SHORT).show();

            }


        });


    }

    private String randomValue(String[] arrayString) {
        int rand = new Random().nextInt(arrayString.length);

        return arrayString[rand].trim();
    }

    @Override
    public void onBackPressed() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this);
        builder.setTitle("Do You Want To Exit?");
        builder.setPositiveButton("Okay", (dialog, which) -> finish());
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        builder.show();
    }
}