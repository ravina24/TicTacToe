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
     *
     * @param view tile that was clicked
     */
    public void makeMove(View view) {
        if (view.equals(sOne)) {
            draw(sOne);
        }

        if (view.equals(sTwo)) {
            draw(sTwo);
        }

        if (view.equals(sThree)) {
            draw(sThree);
        }

        if (view.equals(sFour)) {
            draw(sFour);
        }

        if (view.equals(sFive)) {
            draw(sFive);
        }

        if (view.equals(sSix)) {
            draw(sSix);
        }

        if (view.equals(sSeven)) {
            draw(sSeven);
        }

        if (view.equals(sEight)) {
            draw(sEight);
        }

        if (view.equals(sNine)) {
            draw(sNine);
        }

        checkGameOver();


        // prompt other player to make move
        String player = "Go Player " + turn;
        Toast toast = new Toast(this).makeText(this, player, Toast.LENGTH_SHORT);
        toast.show();


    }

    private void checkGameOver() {
        // get marks from each square

        Boolean row1Over = checkRowOne();
        Boolean row2Over = checkRowTwo();
        Boolean row3Over = checkRowThree();
        Boolean column1Over = checkColumnOne();
        Boolean column2Over = checkColumnTwo();
        Boolean column3Over = checkColumnThree();
        Boolean topRightDiagonalOver = checkTopRightDiagonal();
        Boolean topLeftDiagonalOver = checkTopLeftDiagonal();

        if (row1Over || row2Over || row3Over || column1Over || column2Over || column3Over || topRightDiagonalOver || topLeftDiagonalOver) {
            Toast toast = new Toast(this).makeText(this, "GAME OVER", Toast.LENGTH_LONG);
            toast.setMargin(50, 50);
            toast.show();
        }
    }

    private Boolean checkTopLeftDiagonal() {
        try {
            Drawable sThreeD = sThree.getDrawable();
            Drawable sFiveD = sFive.getDrawable();
            Drawable sSevenD = sSeven.getDrawable();

            Boolean topLeftDiagonalOver = sThreeD.equals(sFiveD) && sThreeD.equals(sSevenD);
            return topLeftDiagonalOver;
        } catch (Exception e) {
            return false;
        }
    }

    private Boolean checkTopRightDiagonal() {
        try {
            Drawable sOneD = sOne.getDrawable();
            Drawable sFiveD = sFive.getDrawable();
            Drawable sNineD = sNine.getDrawable();

            Boolean topRightDiagonalOver = sOneD.equals(sFiveD) && sOneD.equals(sNineD);
            return topRightDiagonalOver;
        } catch (Exception e) {
             return false;
        }
    }

    private Boolean checkColumnThree() {
        try {
            Drawable sThreeD = sThree.getDrawable();
            Drawable sSixD = sSix.getDrawable();
            Drawable sNineD = sNine.getDrawable();

            Boolean column3Over = sThreeD.equals(sSixD) && sThreeD.equals(sNineD);
            return column3Over;
        } catch (Exception e) {
            return false;
        }
    }

    private Boolean checkColumnTwo() {
        try {
            Drawable sTwoD = sTwo.getDrawable();
            Drawable sFiveD = sFive.getDrawable();
            Drawable sEightD = sEight.getDrawable();

            Boolean column2Over = sTwoD.equals(sFiveD) && sTwoD.equals(sEightD);
            return column2Over;
        } catch (Exception e) {
            return false;
        }
    }

    private Boolean checkColumnOne() {
        try {
            Drawable sOneD = sOne.getDrawable();
            Drawable sFourD = sFour.getDrawable();
            Drawable sSevenD = sSeven.getDrawable();

            Boolean column1Over = sOneD.equals(sFourD) && sOneD.equals(sSevenD);
            return column1Over;
        } catch (Exception e) {
            return false;
        }
    }

    private Boolean checkRowThree() {
        try {
            Drawable sSevenD = sSeven.getDrawable();
            Drawable sEightD = sEight.getDrawable();
            Drawable sNineD = sNine.getDrawable();

            Boolean row3Over = sSevenD.equals(sEightD) && sSevenD.equals(sNineD);
            return  row3Over;
        } catch (Exception e) {
            return false;
        }
    }

    private Boolean checkRowTwo() {
        try {
            Drawable sFourD = sFour.getDrawable();
            Drawable sFiveD = sFive.getDrawable();
            Drawable sSixD = sSix.getDrawable();

            Boolean row2Over = sFourD.equals(sFiveD) && sFourD.equals(sSixD);
            return row2Over;
        } catch (Exception e) {
            return false;
        }

    }

    private Boolean checkRowOne() {
        try {
            Drawable sOneD = sOne.getDrawable();
            Drawable sTwoD = sTwo.getDrawable();
            Drawable sThreeD = sThree.getDrawable();

            Boolean row1Over = sOneD.equals(sTwoD) && sOneD.equals(sThreeD);

            return row1Over;
        } catch (Exception e) {
            return false;
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
