package com.example.ravina.tictactoe;

import android.graphics.drawable.Drawable;
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

    Boolean sONeMarked;
    Boolean sTwoMarked;
    Boolean sThreeMarked;
    Boolean sFourMarked;
    Boolean sFiveMarked;
    Boolean sSixMarked;
    Boolean sSevenMarked;
    Boolean sEightMarked;
    Boolean sNineMarked;

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

        sONeMarked = false;
        sTwoMarked = false;
        sThreeMarked = false;
        sFourMarked = false;
        sFiveMarked = false;
        sSixMarked = false;
        sSevenMarked = false;
        sEightMarked = false;
        sNineMarked = false;

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
            sONeMarked = true;
        }

        if(view.equals(sTwo)) {
            draw(sTwo);
            sTwoMarked = true;
        }

        if(view.equals(sThree)) {
            draw(sThree);
            sThreeMarked = true;
        }

        if(view.equals(sFour)) {
            draw(sFour);
            sFourMarked = true;
        }

        if(view.equals(sFive)) {
            draw(sFive);
            sFiveMarked = true;
        }

        if(view.equals(sSix)) {
            draw(sSix);
            sSixMarked = true;
        }

        if(view.equals(sSeven)) {
            draw(sSeven);
            sSevenMarked = true;
        }

        if(view.equals(sEight)) {
            draw(sEight);
            sEightMarked = true;
        }

        if(view.equals(sNine)) {
            draw(sNine);
            sNineMarked = true;
        }

        checkGameOver();


        // prompt other player to make move
        String player = "Go Player " + turn;
        Toast toast = new Toast(this).makeText(this, player, Toast.LENGTH_SHORT);
        toast.show();


    }

    private void checkGameOver() {
        // get marks from each square
        try {
            Drawable sOneD = sOne.getDrawable();
            Drawable sTwoD = sTwo.getDrawable();
            Drawable sThreeD = sThree.getDrawable();
            Drawable sFourD = sFour.getDrawable();
            Drawable sFiveD = sFive.getDrawable();
            Drawable sSixD = sSix.getDrawable();
            Drawable sSevenD = sSeven.getDrawable();
            Drawable sEightD = sEight.getDrawable();
            Drawable sNineD = sNine.getDrawable();

            Boolean row1Over = sOneD.equals(sTwoD) && sOneD.equals(sThreeD);
            Boolean row2Over = sFourD.equals(sFiveD) && sFourD.equals(sSixD);
            Boolean row3Over = sSevenD.equals(sEightD) && sSevenD.equals(sNineD);

            Boolean column1Over = sOneD.equals(sFourD) && sOneD.equals(sSevenD);
            Boolean column2Over = sTwoD.equals(sFiveD) && sTwoD.equals(sEightD);
            Boolean column3Over = sThreeD.equals(sSixD) && sThreeD.equals(sNineD);

            Boolean topRightDiagonalOver = sOneD.equals(sFiveD) && sOneD.equals(sNineD);
            Boolean topLeftDiagonalOver = sThreeD.equals(sFiveD) && sThreeD.equals(sSevenD);

            if(row1Over || row2Over || row3Over || column1Over || column2Over || column3Over || topRightDiagonalOver || topLeftDiagonalOver) {
                Toast toast = new Toast(this).makeText(this, "GAME OVER", Toast.LENGTH_LONG);
                toast.setMargin(50, 50);
                toast.show();
            }
        } catch (NullPointerException e) {
            // game is not over!
        }

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
