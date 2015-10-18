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

    // current implementation doesn't allow drawing from a discard pile.
    // therefore drawCard() is staying in Deck subclass to prevent accidental use.

    public Deck convertToDeck() {
        // converts the pile to a deck that can be drawn from
        Deck deck = new Deck();
        // clear the cards autoloaded into the deck by the constructor.
        deck.getDeck().clear();
        // add the elements of the pile to the new deck object
        deck.getDeck().addAll(this.pile);
        // shuffle the new deck
        deck.shuffle();
        // and return it.
        return deck;
    }
}
