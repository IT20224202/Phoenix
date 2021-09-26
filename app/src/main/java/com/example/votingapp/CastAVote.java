package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CastAVote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_avote);
        getSupportActionBar().hide();
    }
}