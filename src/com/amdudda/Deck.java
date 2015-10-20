package com.amdudda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by amdudda on 10/12/2015.
 */
public class Deck extends Pile {
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

//        TODO: why does this not inherit?
//      I don't actually want .shuffle in Pile, but it's important to understand for future coding.
//      this.drawPile.shuffle();

        // shuffle the deck
        Collections.shuffle(this.drawPile);

    }  // end constructor

    // DONE: Second constructor that enables me to convert an existing Stack (or do I want to keep Pile object?) to a Deck.
    public Deck(Stack<Card> pile_of_cards) {
        // overloading to let me convert a Stack (Pile?  can be coded now and final decision made later- minimal revisions needed.) to a Deck
        this.drawPile = new Stack<Card>();
        // copy the pile of cards to the deck
        this.drawPile.addAll(pile_of_cards);
        // shuffle the deck
        Collections.shuffle(this.drawPile);
        // QUESTION: do we want to automatically clear pile_of_cards?
        // For crazy eights, yes, because we want to have a new discard pile
        // TODO: top of discard stays on play area and is the seed for the new discard pile.
    }

    // getter for deck - duplicates getPile(), but useful for nomenclature match
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
