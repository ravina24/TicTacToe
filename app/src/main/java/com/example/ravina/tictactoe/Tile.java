package com.example.ravina.tictactoe;

import android.widget.ImageView;

/**
 * Created by Ravina on 2018-01-21.
 */

public class Tile {
    Boolean marked;
    ImageView iv;

    /**
     * Constructs an unmarked tile on the board with given imageview
     * @param iv imageview
     */
    public Tile(ImageView iv) {
        this.iv = iv;
        marked = false;
    }

    /**
     * returns if tile is marked
     */
    public Boolean isMarked() {
        return marked;
    }

    /**
     * sets marked to b
     * @param b boolean
     */
    public void setMarked(Boolean b) {
        marked = b;
    }

    // returns imageview
    public ImageView getIv() {
        return iv;
    }
}
