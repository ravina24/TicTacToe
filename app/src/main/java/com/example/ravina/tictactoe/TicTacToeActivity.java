package com.example.ravina.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

public class TicTacToeActivity extends AppCompatActivity {
    GridLayout board;

    ImageView sOne;
    ImageView sTwo;
    ImageView sThree;
    ImageView sFour;
    ImageView sFive;
    ImageView sSix;
    ImageView sSeven;
    ImageView sEight;
    ImageView sNine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        board = (GridLayout) findViewById(R.id.board);


        sOne = (ImageView) findViewById(R.id.sOne);
        sTwo = (ImageView) findViewById(R.id.sTwo);
        sThree = (ImageView) findViewById(R.id.sThree);
        sFour = (ImageView) findViewById(R.id.sFour);
        sFive = (ImageView) findViewById(R.id.sFive);
        sSix = (ImageView) findViewById(R.id.sSix);
        sSeven = (ImageView) findViewById(R.id.sSeven);
        sEight = (ImageView) findViewById(R.id.sEight);
        sNine = (ImageView) findViewById(R.id.sNine);


    }

}
