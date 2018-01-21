package com.example.ravina.tictactoe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.Map;

public class TicTacToeActivity extends AppCompatActivity {
    public static final String SCORES_NAME = "MyScores";


    GridLayout board;
    Button rematchButton;
    TextView scoreOne;
    TextView scoreTwo;

    int turn;
    int p1Score, p2Score;

    Tile t1, t2, t3, t4, t5, t6, t7, t8, t9;

    ImageView sOne;
    ImageView sTwo;
    ImageView sThree;
    ImageView sFour;
    ImageView sFive;
    ImageView sSix;
    ImageView sSeven;
    ImageView sEight;
    ImageView sNine;

    List<ImageView> squares; // list of image views

//    Boolean sOneMarked;
//    Boolean sTwoMarked;
//    Boolean sThreeMarked;
//    Boolean sFourMarked;
//    Boolean sFiveMarked;
//    Boolean sSixMarked;
//    Boolean sSevenMarked;
//    Boolean sEightMarked;
//    Boolean sNineMarked;

    Boolean crossWon;
    Boolean circleWon;
    Boolean isTieGame;
    Boolean isGameOver;

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


        //addSquares();

//        sOneMarked = false;
//        sTwoMarked = false;
//        sThreeMarked = false;
//        sFourMarked = false;
//        sFiveMarked = false;
//        sSixMarked = false;
//        sSevenMarked = false;
//        sEightMarked = false;
//        sNineMarked = false;

        crossWon = false;
        circleWon = false;
        isTieGame = false;
        isGameOver = false;

        turn = 1;



//        // Remember scores from previous games
//        SharedPreferences settings = getSharedPreferences(SCORES_NAME, 0);
//        p1Score = settings.getInt("scoreOne", 0);
//        p2Score = settings.getInt("scoreTwo", 0);
//        updateScores();

        p1Score = Integer.parseInt(getPreference("scoreOne", "0", this));
        p2Score = Integer.parseInt(getPreference("scoreTwo", "0", this));
        updateScores();


        //so the invisible area won't be clicked
        rematchButton.setClickable(false);

        // prompt other player to make move
        String player = "Go Player 1";
        Toast toast = new Toast(this).makeText(this, player, Toast.LENGTH_LONG);
        toast.show();


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
     * creates tiles from imageviews
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
    }

    /**
     * adds squares to the List<ImageView>
     */
    private void addSquares() {
        squares = new ArrayList<>();
        squares.add(sOne);
        squares.add(sTwo);
        squares.add(sThree);
        squares.add(sFour);
        squares.add(sFive);
        squares.add(sSix);
        squares.add(sSeven);
        squares.add(sEight);
        squares.add(sNine);
    }





    /**
     * If move is valid, draw it and set the tile to marked.
     *
     * @param view imageview that was clicked
     */
    public void makeMove(View view) {

        if (!isGameOver) {
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


        isGameOver = checkGameOver();

        if (isGameOver && crossWon) {
            Toast toast = new Toast(this).makeText(this, "GAME OVER, PLAYER 1 WINS", Toast.LENGTH_LONG);
            toast.setMargin(0, 0);
            toast.show();
            p1Score++;
            updateScores();

            rematchButton.setVisibility(View.VISIBLE);
            rematchButton.setClickable(true);

        } else if (isGameOver && circleWon) {
            Toast toast = new Toast(this).makeText(this, "GAME OVER, PLAYER 2 WINS", Toast.LENGTH_LONG);
            toast.setMargin(0, 0);
            toast.show();
            p2Score++;
            updateScores();

            rematchButton.setVisibility(View.VISIBLE);
            rematchButton.setClickable(true);

        } else if (isGameOver && isTieGame) {
            Toast toast = new Toast(this).makeText(this, "GAME OVER, TIE GAME", Toast.LENGTH_LONG);
            toast.setMargin(0, 0);
            toast.show();
            rematchButton.setVisibility(View.VISIBLE);
            rematchButton.setClickable(true);

        } else if (!isGameOver){
            // prompt other player to make move
            String player = "Go Player " + turn;
            Toast toast = new Toast(this).makeText(this, player, Toast.LENGTH_SHORT);
            toast.show();
        }


    }

    /**
     * checks if move is valid
     * @param view imageview that was clicked
     * @param t tile on board
     * @return true if view matches the tile's imageview and if the tile is not marked. False otherwise.
     *          Note: view matches tile's imageview if view id matches tag of tile's iamgeview
     */
    private boolean validMove(View view, Tile t) {
        int tileIvId = (int) t.getIv().getTag();

        return view.getId() == tileIvId && !t.isMarked();
    }

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
        Boolean row1Over = checkRowOne();
        Boolean row2Over = checkRowTwo();
        Boolean row3Over = checkRowThree();
        Boolean column1Over = checkColumnOne();
        Boolean column2Over = checkColumnTwo();
        Boolean column3Over = checkColumnThree();
        Boolean topRightDiagonalOver = checkTopRightDiagonal();
        Boolean topLeftDiagonalOver = checkTopLeftDiagonal();


        if (row1Over || row2Over || row3Over || column1Over || column2Over || column3Over
                || topRightDiagonalOver || topLeftDiagonalOver || tieGame) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean checkBoardFilled() {
        try {
            Drawable.ConstantState sOneD = sOne.getDrawable().getConstantState();
            Drawable.ConstantState sTwoD = sTwo.getDrawable().getConstantState();
            Drawable.ConstantState sThreeD = sThree.getDrawable().getConstantState();
            Drawable.ConstantState sFourD = sFour.getDrawable().getConstantState();
            Drawable.ConstantState sFiveD = sFive.getDrawable().getConstantState();
            Drawable.ConstantState sSixD = sSix.getDrawable().getConstantState();
            Drawable.ConstantState sSevenD = sSeven.getDrawable().getConstantState();
            Drawable.ConstantState sEightD = sEight.getDrawable().getConstantState();
            Drawable.ConstantState sNineD = sNine.getDrawable().getConstantState();

            Drawable sNineDrawable = sNine.getDrawable();
            Drawable defaultBackground = getResources().getDrawable(R.drawable.defaultbackground);


            // in case of rematch when all squares are transparent, I need to guard with:
            Boolean rematchScenario = areDrawablesIdentical(sNineDrawable, defaultBackground);

            if (sOneD != null && sTwoD != null && sThreeD != null && sFourD != null && sFiveD != null && sSixD != null
                    && sSevenD != null && sEightD != null && sNineD != null && !rematchScenario) {
                isTieGame = true;
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

    }

    private Boolean checkTopLeftDiagonal() {
        try {
            Drawable sThreeD = sThree.getDrawable();
            Drawable sFiveD = sFive.getDrawable();
            Drawable sSevenD = sSeven.getDrawable();

            Boolean topLeftDiagonalOver = areDrawablesIdentical(sThreeD, sFiveD) && areDrawablesIdentical(sThreeD, sSevenD);

            // to determine which player won
            if (topLeftDiagonalOver && !isTieGame) {
                isCross(sThreeD);
            }

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

            // to determine which player won
            if (topRightDiagonalOver && !isTieGame) {
                isCross(sOneD);
            }

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

            // to determine which player won
            if (column3Over && !isTieGame) {
                isCross(sThreeD);
            }

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

            // to determine which player won
            if (column2Over && !isTieGame) {
                isCross(sTwoD);
            }

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

            // to determine which player won
            if (column1Over && !isTieGame) {
                isCross(sOneD);
            }

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

            // to determine which player won
            if (row3Over && !isTieGame) {
                isCross(sSevenD);
            }

            return row3Over;
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

            // to determine which player won
            if (row2Over && !isTieGame) {
                isCross(sFourD);
            }

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

            // to determine which player won
            if (row1Over && !isTieGame) {
                isCross(sOneD);
            }

            return row1Over;
        } catch (Exception e) {
            return false;
        }
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
     * returns true if d is a cross, false otherwise
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
     * restarts application
     */
    public void rematch(View view) {
        // below code is from https://stackoverflow.com/questions/15564614/how-to-restart-an-android-application-programmatically
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );

//        //save scores MY CODE
//        i.putExtra("scoreOne", p1Score);
//        i.putExtra("scoreTwo", p2Score);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(i);

    }

    @Override
    protected  void onStop() {
        super.onStop();

//        // We need an Editor object to make preference changes.
//        // All objects are from android.context.Context
//        SharedPreferences settings = getSharedPreferences(SCORES_NAME, 0);
//        SharedPreferences.Editor editor = settings.edit();
//
//        editor.clear();
//        editor.putInt("scoreOne", p1Score);
//        editor.putInt("scoreTwo", p2Score);
//
//        editor.commit();
        String p1S = "" + p1Score;
        String p2S = "" + p2Score;
        setPreference("scoreOne", p1S, this);
        setPreference("scoreTwo", p2S, this);

    }

//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        super.onSaveInstanceState(savedInstanceState);
//        // Save UI state changes to the savedInstanceState.
//        // This bundle will be passed to onCreate if the process is
//        // killed and restarted.
//        savedInstanceState.putInt("scoreOne", p1Score);
//        savedInstanceState.putInt("scoreTwo", p2Score);
//
//        // etc.
//    }
//
//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        // Restore UI state from the savedInstanceState.
//        // This bundle has also been passed to onCreate.
//
//
//        int p1Score = savedInstanceState.getInt("scoreOne", 0);
//        int p2Score = savedInstanceState.getInt("scoreTwo", 0);
//    }

    static SharedPreferences sh_Pref;
    public static String Preference_Name = "AppData";



    public static String getPreference(String key, String Default,
                                       Activity activity)
    {
        sh_Pref = activity.getSharedPreferences(Preference_Name, Context.MODE_PRIVATE);
        return sh_Pref.getString(key, Default);
    }

    public static void setPreference(String key, String value, Activity activity)
    {
        if (value != null)
        {

            sh_Pref = activity.getSharedPreferences(Preference_Name, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh_Pref.edit();
            editor.putString(key, value);
            editor.commit();


        }

    }
}
