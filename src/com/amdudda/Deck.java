package com.amdudda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by amdudda on 10/12/2015.
 */
public class Deck extends Pile {
    // a Stack storing the draw my_pile
    //protected Stack<Card> my_pile;

    // Constructor
    public Deck() {
        // creates a new draw my_pile
        this.my_pile = new Stack<Card>();

        // set up an interable list of our suits
        ArrayList<String> suits = new ArrayList<String>();
        suits.add("Hearts");
        suits.add("Diamonds");
        suits.add("Clubs");
        suits.add("Spades");

        // and populate our Deck.
        for (String j : suits) {
            for (int i = 1; i < 14; i++) {
                this.my_pile.add(new Card(j, i));
            } // end run through suits
        }  // end run through card numbers

        // shuffle the deck
        Collections.shuffle(this.my_pile);

    }  // end constructor


    // DONE: Second constructor that enables me to convert an existing Stack
    // (or do I want to keep Pile object?) to a Deck.  Not actually used in
    // implementation, so this is mostly an intellectual exercise.
    public Deck(Pile pile_of_cards) {
        // TODO: HAS NOT BEEN TESTED - not implemented in this version of the game!
        // overloading to let me convert a Stack (Pile?  can be coded now and final decision made later- minimal revisions needed.) to a Deck
        this.my_pile = new Stack<Card>();
        // copy the my_pile of cards to the deck
        this.my_pile.addAll(pile_of_cards.getMy_pile());
        // shuffle the deck
        Collections.shuffle(this.my_pile);
        // QUESTION: do we want to automatically clear pile_of_cards?
        // For crazy eights, yes, because we want to have a new discard my_pile
        // DONE: top of discard stays on play area and is the seed for the new discard my_pile.
    }

    // getter for deck - duplicates getMy_pile(), but useful for nomenclature match
    protected Stack<Card> getDeck() {
        // returns the draw my_pile I've generated
        return this.my_pile;
    }

    // misc methods
    public Card drawCard() {
        // draw a card from the Deck
        return this.my_pile.pop();
    }

}
