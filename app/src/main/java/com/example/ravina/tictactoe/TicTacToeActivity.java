package com.example.ravina.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class TicTacToeActivity extends AppCompatActivity {
    GridLayout board;
    int turn;

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

        // initialize fields
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

        turn = 1;

        // prompt other player to make move
        String player = "Go Player 1";
        Toast toast = new Toast(this).makeText(this, player, Toast.LENGTH_LONG);
        toast.show();


    }

    /**
     * If turn is 0, make a cross on view. If turn is 1, make circle on view
     * @param view tile that was clicked
     */
    public void makeMove(View view) {
        if(view.equals(sOne)) {
            draw(sOne);
        }

        if(view.equals(sTwo)) {
            draw(sTwo);
        }

        if(view.equals(sThree)) {
            draw(sThree);
        }

        if(view.equals(sFour)) {
            draw(sFour);
        }

        if(view.equals(sFive)) {
            draw(sFive);
        }

        if(view.equals(sSix)) {
            draw(sSix);
        }

        if(view.equals(sSeven)) {
            draw(sSeven);
        }

        if(view.equals(sEight)) {
            draw(sEight);
        }

        if(view.equals(sNine)) {
            draw(sNine);
        }

        // prompt other player to make move
        String player = "Go Player " + turn;
        Toast toast = new Toast(this).makeText(this, player, Toast.LENGTH_SHORT);
        toast.show();


    }

    public void draw(ImageView iv) {
        if (turn == 1) {
            iv.setImageResource(R.drawable.cross);
            turn++;
        } else {
            iv.setImageResource(R.drawable.circle);
            turn--;
        }
    }

}
