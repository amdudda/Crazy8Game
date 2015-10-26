package com.amdudda;

import java.util.Stack;

/**
 * Created by amdudda on 10/26/2015.
 */
public class DiscardPile extends Pile {

    // inherits from Pile, no new attributes.

    // constructor
    public DiscardPile() {
        this.my_pile = new Stack<Card>();
    }

    protected Deck toDeck() {
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
