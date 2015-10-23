package com.amdudda;

/**
 * Created by amdudda on 10/12/2015.
 */
public class Card {
    // card object for Crazy Eights Game
    // ANSI codes to set card colors
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RESET_COLOR = "\u001B[0m";

    // attributes
    protected String suit;
    protected int value;
    protected String color;

    // constructor
    public Card(String s, int v) {
        this.suit = s;
        this.value = v;
        if (s.equals("Hearts") || s.equals("Diamonds")) this.color = ANSI_RED;
        else this.color = ANSI_BLACK;
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
        return this.color + this.getFace() + " of " + this.suit + ANSI_RESET_COLOR;
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

    protected void fixColor() {
        // updates color for when an 8's suit is declared
        if (this.suit.equals("Hearts") || this.suit.equals("Diamonds")) this.color = ANSI_RED;
        else this.color = ANSI_BLACK;
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
