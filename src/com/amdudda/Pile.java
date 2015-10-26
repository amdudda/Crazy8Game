package com.amdudda;

import java.util.Stack;

/**
 * Created by amdudda on 10/17/15.
 */
public abstract class Pile {

    protected Stack<Card> my_pile;

/*    // constructor
    public Pile() {
        this.my_pile = new Stack<Card>();
    }*/

    // getter for my_pile
    protected Stack<Card> getMy_pile() {
        return this.my_pile;
    }

    // read top card of my_pile
    protected Card topOfPile() {
        return this.my_pile.peek();
    }

    // add card to my_pile
    public void addCard(Card c) {
        this.my_pile.add(c);
    }

    public int getSize() {
        return this.my_pile.size();
    }

    // current implementation doesn't allow drawing from a discard my_pile.
    // therefore drawCard() is staying in Deck subclass to prevent accidental use.

}
