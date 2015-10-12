package com.amdudda;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by amdudda on 10/12/2015.
 */
public class Deck {
    // a Stack storing the draw pile
    protected Stack<Card> draw_pile;

    // Constructor
    public Deck() {
        // creates a new draw pile -  note this hasn't been shuffled!
        Stack<Card> draw_pile = new Stack<Card>();

        // set up an interable list of our suits
        ArrayList<String> suits = new ArrayList<String>();
        suits.add("Hearts");
        suits.add("Diamonds");
        suits.add("Clubs");
        suits.add("Spades");

        // and populate our Deck.
        for (String j : suits) {
            for (int i = 1; i < 14; i++) {
                draw_pile.add(new Card(j,i));
            } // end run through suits
        }  // end run through card numbers
    }  // end constructor

    // getter for deck
    protected Stack<Card> getDeck() {
        // returns the draw pile I've generated
        return this.draw_pile;
    }

    // misc methods

}
