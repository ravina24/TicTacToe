package com.example.ravina.tictactoe;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
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
        Boolean isGameOver;

        if (view.equals(sOne)) {
            draw(sOne);
        } else if (view.equals(sTwo)) {
            draw(sTwo);
        } else if (view.equals(sThree)) {
            draw(sThree);
        } else if (view.equals(sFour)) {
            draw(sFour);
        } else if (view.equals(sFive)) {
            draw(sFive);
        } else if (view.equals(sSix)) {
            draw(sSix);
        } else if (view.equals(sSeven)) {
            draw(sSeven);
            isGameOver = checkGameOver();
        } else if (view.equals(sEight)) {
            draw(sEight);
        } else if (view.equals(sNine)) {
            draw(sNine);
        }

        isGameOver = checkGameOver();

        if(isGameOver) {
            Toast toast = new Toast(this).makeText(this, "GAME OVER", Toast.LENGTH_LONG);
            toast.setMargin(50, 50);
            toast.show();
        } else {
            // prompt other player to make move
            String player = "Go Player " + turn;
            Toast toast = new Toast(this).makeText(this, player, Toast.LENGTH_SHORT);
            toast.show();
        }





    }

    private Boolean checkGameOver() {
        // check all possible game overs
        Boolean row1Over = checkRowOne();
        Boolean row2Over = checkRowTwo();
        Boolean row3Over = checkRowThree();
        Boolean column1Over = checkColumnOne();
        Boolean column2Over = checkColumnTwo();
        Boolean column3Over = checkColumnThree();
        Boolean topRightDiagonalOver = checkTopRightDiagonal();
        Boolean topLeftDiagonalOver = checkTopLeftDiagonal();

        if (row1Over || row2Over || row3Over || column1Over || column2Over || column3Over || topRightDiagonalOver || topLeftDiagonalOver) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean checkTopLeftDiagonal() {
        try {
            Drawable sThreeD = sThree.getDrawable();
            Drawable sFiveD = sFive.getDrawable();
            Drawable sSevenD = sSeven.getDrawable();

            Boolean topLeftDiagonalOver = areDrawablesIdentical(sThreeD, sFiveD) && areDrawablesIdentical(sThreeD, sSevenD);

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

            Boolean topRightDiagonalOver = areDrawablesIdentical(sOneD, sFiveD) && areDrawablesIdentical(sOneD, sNineD);

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

            Boolean column3Over = areDrawablesIdentical(sThreeD, sSixD) && areDrawablesIdentical(sThreeD, sNineD);

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

            Boolean column2Over = areDrawablesIdentical(sTwoD, sFiveD) && areDrawablesIdentical(sTwoD, sEightD);

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

            Boolean column1Over = areDrawablesIdentical(sOneD, sFourD) && areDrawablesIdentical(sOneD, sSevenD);

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

            Boolean row3Over = areDrawablesIdentical(sSevenD, sEightD) && areDrawablesIdentical(sSevenD, sNineD);

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

            Boolean row2Over = areDrawablesIdentical(sFourD, sFiveD) && areDrawablesIdentical(sFourD, sSixD);

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

            Boolean row1Over = areDrawablesIdentical(sOneD, sTwoD) && areDrawablesIdentical(sOneD, sThreeD);

            return row1Over;
        } catch (Exception e) {
            return false;
        }
    }

    private void draw(ImageView iv) {
        if (turn == 1) {
            iv.setImageResource(R.drawable.cross);
            turn++;
        } else {
            iv.setImageResource(R.drawable.circle);
            turn--;
        }
    }

    // below code is not mine
    public static boolean areDrawablesIdentical(Drawable drawableA, Drawable drawableB) {
        Drawable.ConstantState stateA = drawableA.getConstantState();
        Drawable.ConstantState stateB = drawableB.getConstantState();
        // If the constant state is identical, they are using the same drawable resource.
        // However, the opposite is not necessarily true.
        return (stateA != null && stateB != null && stateA.equals(stateB))
                || getBitmap(drawableA).sameAs(getBitmap(drawableB));
    }

    // from online: below code is not mine
    public static Bitmap getBitmap(Drawable drawable) {
        Bitmap result;
        if (drawable instanceof BitmapDrawable) {
            result = ((BitmapDrawable) drawable).getBitmap();
        } else {
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            // Some drawables have no intrinsic width - e.g. solid colours.
            if (width <= 0) {
                width = 1;
            }
            if (height <= 0) {
                height = 1;
            }

            result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(result);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        }
        return result;
    }

}
