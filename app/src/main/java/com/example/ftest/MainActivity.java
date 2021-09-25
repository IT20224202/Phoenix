package com.example.ftest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText name;
    Button btnReg;
    DatabaseReference dbRef;
    Student std;

    private void clearControls(){
        name.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);

        btnReg = findViewById(R.id.btnReg);

        std = new Student();


        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbRef = FirebaseDatabase.getInstance().getReference().child("Student");

                try {
                    if(TextUtils.isEmpty((name.getText().toString())))
                        Toast.makeText(getApplicationContext(), "Please enter name", Toast.LENGTH_SHORT).show();
                    else{
                        std.setName(name.getText().toString().trim());

                        dbRef.push().setValue(std);

                        Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                }
                catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }




}