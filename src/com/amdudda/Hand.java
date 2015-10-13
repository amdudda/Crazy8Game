package com.amdudda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by amdudda on 10/12/2015.
 */
public class Hand {
    // this is an arraylist holding a bunch of cards
    protected ArrayList<Card> hand;
    public static final int START_SIZE = 5;

    // Constructor
    // Done: this seeds a bunch of cards from the draw pile - need to code that first
    public Hand(Deck d) {
        this.hand = new ArrayList<Card>();
        // draw cards from the deck till we've got a starting hand
        for (int i=0;i<START_SIZE; i++) {
            this.hand.add(d.drawCard());
        } // end for
    } // end constructor
}
