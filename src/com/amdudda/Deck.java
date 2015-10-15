package com.amdudda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by amdudda on 10/12/2015.
 */
public class Deck {
    // a Stack storing the draw pile
    protected Stack<Card> drawPile;

    // Constructor
    public Deck() {
        // creates a new draw pile
        this.drawPile = new Stack<Card>();

        // set up an interable list of our suits
        ArrayList<String> suits = new ArrayList<String>();
        suits.add("Hearts");
        suits.add("Diamonds");
        suits.add("Clubs");
        suits.add("Spades");

        // and populate our Deck.
        for (String j : suits) {
            for (int i = 1; i < 14; i++) {
                this.drawPile.add(new Card(j, i));
            } // end run through suits
        }  // end run through card numbers

        // shuffle the deck
        Collections.shuffle(this.drawPile);

    }  // end constructor

    // getter for deck
    protected Stack<Card> getDeck() {
        // returns the draw pile I've generated
        return this.drawPile;
    }

    // misc methods
    public Card drawCard() {
        // draw a card from the Deck
        return this.drawPile.pop();
    }

    public int getSize() {
        return this.drawPile.size();
    }
}
