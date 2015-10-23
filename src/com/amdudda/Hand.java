package com.amdudda;

import java.util.ArrayList;

/**
 * Created by amdudda on 10/12/2015.
 */
public class Hand {
    // this is an arraylist holding a bunch of cards
    protected ArrayList<Card> myHand;
    private static final int START_SIZE = 5;  // makes default hand size easy to alter

    // Constructor
    // Done: this seeds a bunch of cards from the draw my_pile - need to code that first
    public Hand() {
        this.myHand = new ArrayList<Card>();
        // draw cards from the deck till we've got a starting hand
        for (int i=0;i<START_SIZE; i++) {
            this.myHand.add(CrazyEightsGame.gameDeck.drawCard());
        } // end for
    } // end constructor

    @Override
    public String toString(){
        // prints out the contents of the hand
        String list_of_cards = "\u001B[0m";  // make sure to set any colors back to their default before printing the list
        for (int i=0; i<this.myHand.size(); i++) {
            list_of_cards += (i+1) + ".) " + this.getMyHand().get(i) + "\n";
        }
       return list_of_cards;
    }

    // getter for myHand
    public ArrayList<Card> getMyHand() {
        return this.myHand;
    }

    // misc methods

    public int getSize() { return this.myHand.size(); }

    public void addCard(Card c) {
        // adds card to the myHand
        this.myHand.add(c);
    }

    public Card getCardFromHand(int i) {
        return this.myHand.get(i);
    }

    public void dropCard(Card c) {
        this.myHand.remove(c);
    }

    public int getHandPoints() {
        int points = 0;
        for (Card c:this.myHand) {
            points += c.value;
        }
        return points;
    }
}
