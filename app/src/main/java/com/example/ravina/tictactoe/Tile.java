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


    // A tile is equal to another tile if they have the same imageview
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile tile = (Tile) o;

        return iv != null ? iv.equals(tile.iv) : tile.iv == null;
    }

    @Override
    public int hashCode() {
        return iv != null ? iv.hashCode() : 0;
    }
}
