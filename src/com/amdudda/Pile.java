package com.amdudda;

import java.util.Collections;
import java.util.Stack;

/**
 * Created by amdudda on 10/17/15.
 */
public class Pile {

    protected Stack<Card> pile;

    // constructor
    public Pile() {
        this.pile = new Stack<Card>();
    }

    // getter for pile
    protected Stack<Card> getPile() {
        return  this.pile;
    }

    // read top card of pile
    protected Card topOfPile() {
        return this.pile.peek();
    }

    // add card to pile
    public void addCard(Card c) {
        this.pile.add(c);
    }

    public void shuffle() {
        // shuffles a pile or deck
        Collections.shuffle(this.pile);
    }

    public int getSize() {
        return this.pile.size();
    }

    // current implementation doesn't allow drawing from a discard pile.
    // therefore drawCard() is staying in Deck subclass to prevent accidental use.

}
