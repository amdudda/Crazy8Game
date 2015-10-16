package com.amdudda;

/**
 * Created by amdudda on 10/12/2015.
 */
public class Card {
    // card object for Crazy Eights Game

    protected String suit;
    protected int value;

    // constructor
    public Card(String s, int v) {
        this.suit = s;
        this.value = v;
    }

    // setters and getters
    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    // end setters and getters

    // misc methods
    @Override
    public String toString() {
        return this.getFace() + " of " + this.suit;
    }

    private String getFace() {
        switch (this.value) {
            case 1: return "Ace";
            case 11: return "Jack";
            case 12: return "Queen";
            case 13: return "King";
            default: return this.value + "";
        }
    }

    protected boolean isLegalToPlayOn(Card c) {
        // decides whether the card is a legal play
        // longer name to make it clearer what it's actually evaluating
        if (this.value == 8 || this.suit.equals(c.getSuit()) || this.getValue() == c.getValue()) {
            return true;
        }
        return false;
    }
}
