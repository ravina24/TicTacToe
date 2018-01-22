package com.example.ravina.tictactoe;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeActivity extends AppCompatActivity {
    GridLayout board;
    Button rematchButton;
    TextView scoreOne, scoreTwo;

    int turn, p1Score, p2Score;

    Tile t1, t2, t3, t4, t5, t6, t7, t8, t9;

    ImageView sOne, sTwo, sThree, sFour, sFive, sSix, sSeven, sEight, sNine;

    List<Tile> tiles;

    Boolean crossWon, circleWon, isTieGame, isGameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        // initialize fields
        board = (GridLayout) findViewById(R.id.board);
        rematchButton = (Button) findViewById(R.id.buttonRematch);
        scoreOne = (TextView) findViewById(R.id.scoreOne);
        scoreTwo = (TextView) findViewById(R.id.scoreTwo);

        assignImageViews();

        assignTiles();

        // set scores to 0
        p1Score = 0;
        p2Score = 0;


        matchBeginState();




    }

    // assigns each imageview and matches ids by setting the id of the actual imageview to the copy's tag
    private void assignImageViews() {
        sOne = (ImageView) findViewById(R.id.sOne);
        sOne.setTag(R.id.sOne);

        sTwo = (ImageView) findViewById(R.id.sTwo);
        sTwo.setTag(R.id.sTwo);

        sThree = (ImageView) findViewById(R.id.sThree);
        sThree.setTag(R.id.sThree);

        sFour = (ImageView) findViewById(R.id.sFour);
        sFour.setTag(R.id.sFour);

        sFive = (ImageView) findViewById(R.id.sFive);
        sFive.setTag(R.id.sFive);

        sSix = (ImageView) findViewById(R.id.sSix);
        sSix.setTag(R.id.sSix);

        sSeven = (ImageView) findViewById(R.id.sSeven);
        sSeven.setTag(R.id.sSeven);

        sEight = (ImageView) findViewById(R.id.sEight);
        sEight.setTag(R.id.sEight);

        sNine = (ImageView) findViewById(R.id.sNine);
        sNine.setTag(R.id.sNine);
    }

    /**
     * creates tiles from imageviews and adds all tiles to tiles
     */
    private void assignTiles() {
        t1 = new Tile(sOne);
        t2 = new Tile(sTwo);
        t3 = new Tile(sThree);
        t4 = new Tile(sFour);
        t5 = new Tile(sFive);
        t6 = new Tile(sSix);
        t7 = new Tile(sSeven);
        t8 = new Tile(sEight);
        t9 = new Tile(sNine);

        tiles = new ArrayList<>();

        tiles.add(t1);
        tiles.add(t2);
        tiles.add(t3);
        tiles.add(t4);
        tiles.add(t5);
        tiles.add(t6);
        tiles.add(t7);
        tiles.add(t8);
        tiles.add(t9);
    }

    /**
     * If game is not over, check if move is valid. If yes, draw it and set the tile to marked.
     * Then check if game is over. If yes, do game over stuff. If no, continue game.
     *
     * @param view imageview that was clicked
     */
    public void makeMove(View view) {
        if (!isGameOver) {
            handleMove(view);
        }

        isGameOver = checkGameOver();

        if(isGameOver) {
            handleGameOver();
        } else {
            // prompt other player to make move
            String player = "Go Player " + turn;
            Toast toast = new Toast(this).makeText(this, player, Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    /**
     * makes move checking validity, etc.
     * @param view imageview that was clicked
     */
    private void handleMove(View view) {
        if (validMove(view, t1)) {
            draw(sOne);
            t1.setMarked(true);
        } else if (validMove(view, t2)) {
            draw(sTwo);
            t2.setMarked(true);
        } else if (validMove(view, t3)) {
            draw(sThree);
            t3.setMarked(true);
        } else if (validMove(view, t4)) {
            draw(sFour);
            t4.setMarked(true);
        } else if (validMove(view, t5)) {
            draw(sFive);
            t5.setMarked(true);
        } else if (validMove(view, t6)) {
            draw(sSix);
            t6.setMarked(true);
        } else if (validMove(view, t7)) {
            draw(sSeven);
            t7.setMarked(true);
        } else if (validMove(view, t8)) {
            draw(sEight);
            t8.setMarked(true);
        } else if (validMove(view, t9)) {
            draw(sNine);
            t9.setMarked(true);
        }
    }

    /**
     * make toast of player that won (or tie game). Update score.
     */
    private void handleGameOver() {
        if (crossWon) {
            Toast toast = new Toast(this).makeText(this, "GAME OVER, PLAYER 1 WINS", Toast.LENGTH_LONG);
            toast.setMargin(0, 0);
            toast.show();
            p1Score++;
            updateScores();

            rematchButton.setVisibility(View.VISIBLE);
            rematchButton.setClickable(true);

        } else if (circleWon) {
            Toast toast = new Toast(this).makeText(this, "GAME OVER, PLAYER 2 WINS", Toast.LENGTH_LONG);
            toast.setMargin(0, 0);
            toast.show();
            p2Score++;
            updateScores();

            rematchButton.setVisibility(View.VISIBLE);
            rematchButton.setClickable(true);

        } else if (isTieGame) {
            Toast toast = new Toast(this).makeText(this, "GAME OVER, TIE GAME", Toast.LENGTH_LONG);
            toast.setMargin(0, 0);
            toast.show();
            rematchButton.setVisibility(View.VISIBLE);
            rematchButton.setClickable(true);

        } else {
            Toast toast = new Toast(this).makeText(this, "Something went wrong", Toast.LENGTH_LONG);
            toast.setMargin(0, 0);
            toast.show();

        }
    }

    /**
     * checks if move is valid
     * @param view imageview that was clicked
     * @param t tile on board
     * @return true if view matches the tile's imageview and if the tile is not marked. False otherwise.
     *          Note: view matches tile's imageview if view id matches tag of tile's imageview
     */
    private boolean validMove(View view, Tile t) {
        int tileIvId = (int) t.getIv().getTag();

        return view.getId() == tileIvId && !t.isMarked();
    }

    /**
     * sets scoreOne text to p1Score and scoreTwo text to p2Score
     */
    private void updateScores() {
        String p1S = "" + p1Score;
        String p2S = "" + p2Score;

        scoreOne.setText(p1S);
        scoreTwo.setText(p2S);
    }

    private Boolean checkGameOver() {
        // check if board is filled
        Boolean tieGame = checkBoardFilled();

        // check all possible game overs for Win Situations
        Boolean row1Over = checkOver(t1, t2, t3);
        Boolean row2Over = checkOver(t4, t5, t6);
        Boolean row3Over = checkOver(t7, t8, t9);
        Boolean column1Over = checkOver(t1, t4, t7);
        Boolean column2Over = checkOver(t2, t5, t8);
        Boolean column3Over = checkOver(t3, t6, t9);
        Boolean topRightDiagonalOver = checkOver(t3, t5, t7);
        Boolean topLeftDiagonalOver = checkOver(t1, t5, t9);


        if (row1Over || row2Over || row3Over || column1Over || column2Over || column3Over
                || topRightDiagonalOver || topLeftDiagonalOver) { // winning scenarios
            return true;
        } else if (tieGame) { //because player may win on last try
            return true;
        } else {
            return false;
        }
    }

    /**
     * board is filled when each tile is marked. if board is filled, set isTieGame to true.
     * @return true if all tiles are marked, false otherwise
     */
    private Boolean checkBoardFilled() {
        Boolean allTilesMarked = true;

        for(Tile t : tiles) {
            if(!t.isMarked()) {
                allTilesMarked = false;
                break;
            }
        }

        if(allTilesMarked) {
            isTieGame = true;
            return true;
        } else {
            return false;
        }

    }

    /**
     * @param t1 tile 1
     * @param t2 tile 2
     * @param t3 tile 3
     * @return true if t1, t2, t3 have the same drawables and are all marked. False otherwise
     */
    private Boolean checkOver(Tile t1, Tile t2, Tile t3) {
        Drawable t1D = t1.getIv().getDrawable();
        Drawable t2D = t2.getIv().getDrawable();
        Drawable t3D = t3.getIv().getDrawable();

        Boolean hasSameDrawables = areDrawablesIdentical(t1D, t2D) && areDrawablesIdentical(t1D, t3D);
        Boolean allTilesMarked = t1.isMarked() && t2.isMarked() && t3.isMarked();

        Boolean over = hasSameDrawables && allTilesMarked;

        // to determine which player won
        if(over) {
            isCross(t1.getIv().getDrawable());
        }

        return over;
    }

    /**
     * If turn is 1, draw a cross on iv. Else, draw a circle on iv
     * @param iv imageview to draw on
     */
    private void draw(ImageView iv) {
        if (turn == 1) {
            iv.setImageResource(R.drawable.cross);
            turn++;
        } else {
            iv.setImageResource(R.drawable.circle);
            turn--;
        }
    }

    // below code is from https://spotandroid.com/2017/02/15/android-tricks-how-to-compare-two-drawables/
    public static boolean areDrawablesIdentical(Drawable drawableA, Drawable drawableB) {
        Drawable.ConstantState stateA = drawableA.getConstantState();
        Drawable.ConstantState stateB = drawableB.getConstantState();
        // If the constant state is identical, they are using the same drawable resource.
        // However, the opposite is not necessarily true.
        return (stateA != null && stateB != null && stateA.equals(stateB))
                || getBitmap(drawableA).sameAs(getBitmap(drawableB));
    }

    // below code is from https://spotandroid.com/2017/02/15/android-tricks-how-to-compare-two-drawables/
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

    /**
     * sets crossWon to true if d is a cross, otherwise sets circleWon to true
     *
     * @param d Drawable d to determine shape of
     */
    private void isCross(Drawable d) {
        Drawable cross = getResources().getDrawable(R.drawable.cross);
        Drawable circle = getResources().getDrawable(R.drawable.circle);

        if (areDrawablesIdentical(d, cross)) {
            crossWon = true;
        } else if (areDrawablesIdentical(d, circle)) {
            circleWon = true;
        }

    }

    /**
     * clear each tile's imageview and set the tile to marked. Put app in matchBeginState
     * @param view
     */
    public void rematch(View view) {
        for(Tile t : tiles) {
            t.getIv().setImageDrawable(getResources().getDrawable(R.drawable.defaultbackground));
            t.setMarked(false);
        }

        matchBeginState();
    }

    /**
     * sets isGameOver, isTieGame, crossWon, circleWon to false and hides rematch button. Sets turn to 1
     */
    private void matchBeginState() {
        crossWon = false;
        circleWon = false;
        isTieGame = false;
        isGameOver = false;

        turn = 1;
        rematchButton.setVisibility(View.INVISIBLE);
        rematchButton.setClickable(false);

        // prompt first player to make move
        String player = "Go Player 1";
        Toast toast = new Toast(this).makeText(this, player, Toast.LENGTH_LONG);
        toast.show();
    }

}
