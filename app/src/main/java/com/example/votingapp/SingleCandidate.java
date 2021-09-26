package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.votingapp.database.DBCandidate;
import com.example.votingapp.database.DBEvent;

public class SingleCandidate extends AppCompatActivity {

    TextView name, description, id;
    Button updateC, deleteC;
    DBCandidate DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_candidate);
        getSupportActionBar().hide();

        name = findViewById(R.id.singleC_name);
        description = findViewById(R.id.singleC_desc);
        id = findViewById(R.id.singleC_id);

        updateC = findViewById(R.id.singleC_edit);
        deleteC = findViewById(R.id.singleC_delete);

        //update
        updateC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String descTXT = description.getText().toString();
                String idTXT = id.getText().toString();
                Boolean checkUpdateCandidate = DB.updateCandidate(nameTXT, descTXT, idTXT);
                if (checkUpdateCandidate == true)
                    Toast.makeText(SingleCandidate.this, "Candidate updated!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(SingleCandidate.this, "Candidate not updated!", Toast.LENGTH_SHORT).show();
            }
        });
        //delete
        deleteC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idTXT = id.getText().toString();
                Boolean checkDeleteEvent = DB.deleteCandidate(idTXT);
                if (checkDeleteEvent == true)
                    Toast.makeText(SingleCandidate.this, "Candidate deleted!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(SingleCandidate.this, "Candidate not deleted!", Toast.LENGTH_SHORT).show();
            }
        });
        //show data
        showCandidateData();
    }

    private void showCandidateData() {

        Cursor res = DB.getCandidates();

        String candidate_name = res.getString(0);
        String candidate_desc = res.getString(1);
        String candidate_id = res.getString(2);

        name.setText(candidate_name);
        description.setText(candidate_desc);
        id.setText(candidate_id);
    }
}