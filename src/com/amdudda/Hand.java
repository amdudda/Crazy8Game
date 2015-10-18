package com.amdudda;

import java.util.ArrayList;

/**
 * Created by amdudda on 10/12/2015.
 */
public class Hand {
    // this is an arraylist holding a bunch of cards
    protected ArrayList<Card> hand;
    public static final int START_SIZE = 5;

    // Constructor
    // Done: this seeds a bunch of cards from the draw pile - need to code that first
    public Hand() {
        this.hand = new ArrayList<Card>();
        // draw cards from the deck till we've got a starting hand
        for (int i=0;i<START_SIZE; i++) {
            this.hand.add(CrazyEightsGame.gameDeck.drawCard());
        } // end for
    } // end constructor

    @Override
    public String toString(){
        // prints out the contents of the hand
        String list_of_cards = "\u001B[0m";  // make sure to set any colors back to their default before printing the list
        for (int i=0; i<this.hand.size(); i++) {
            list_of_cards += (i+1) + ".) " + this.getHand().get(i) + "\n";
        }
       return list_of_cards;
    }

    // getter for hand
    public ArrayList<Card> getHand() {
        return this.hand;
    }

    // misc methods

    public int getSize() { return this.hand.size(); }

    public void addCard(Card c) {
        // adds card to the hand
        this.hand.add(c);
    }

    public Card getCardFromHand(int i) {
        return this.hand.get(i);
    }

    public void dropCard(Card c) {
        this.hand.remove(c);
    }
}
