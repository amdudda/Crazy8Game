package com.amdudda;

import java.util.Stack;

/**
 * Created by amdudda on 10/17/15.
 */
public class Pile {

    protected Stack<Card> my_pile;

    // constructor
    public Pile() {
        this.my_pile = new Stack<Card>();
    }

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

    public Deck convertToDeck() {
        // TODO: HAS NOT BEEN TESTED - not implemented in this version of the game!
        /*
         Top of discard stays on play area and is the seed for the new discard pile.
         so we need a way to convert discard pile to a deck without messing with the
         second deck constructor.
        */
        // convert a my_pile to a deck
        // keep the top card of the my_pile
        Card top_of_deck = this.getMy_pile().pop();
        // create the deck from the remaining cards
        Deck fresh_deck = new Deck(this);
        // clear the my_pile
        this.getMy_pile().clear();
        // and put the top card back on it
        this.addCard(top_of_deck);
        // and don't forget to return the new deck
        return fresh_deck;
    }
}
