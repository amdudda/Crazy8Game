package com.amdudda;

import java.awt.*;

/**
 * Created by amdudda on 10/12/15.
 */
public abstract class Player {
    // Player class, will split into polymorphic Human and Computer classes
    // some string constants to help set player colors.
    protected static final String ANSI_RED = "\u001B[31m";
    protected static final String ANSI_BLUE = "\u001B[34m";
    protected static final String ANSI_RESET_COLOR = "\u001B[0m";

    // attributes of all players - protected instead of private so sublcasses can access & inherit
    protected String name;
    protected Hand playerHand;
    protected String playerColor;
    protected int score;

    /*// Constructor
    public Player(String pName) {
        // creates a player and deals them a hand
        this.name = pName;
        this.playerHand = new Hand();
    }*/

    //public abstract Card pickCard();
    public abstract void takeTurn();
    public abstract String pickSuit();

    @Override
    public String toString() {
        return this.name + "'s hand is: \n" + playerHand.toString();
    }

    // setter & getter for name & score
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() { return this.score; }

    public void setScore(int gameScore) {
        this.score = gameScore;
    }

    protected Hand getPlayerHand() { return this.playerHand; }

    // misc methods
    public void drawCard(Deck d){
        // draw a card from the deck and add it to the player's hand
        // I'm passing a Deck variable here so the program is extensible to games with multiple decks to draw from
        Card card_to_add = d.drawCard();
        if (!this.name.equals("The computer")) {
            // hide what the computer draws from the other player(s)
            System.out.println(Colorize(this.name + " draws a " + card_to_add + "."));
        }
        // it's Player's job to draw a card, but it's Hand object's responsibility to keep track of it
        this.playerHand.addCard(card_to_add);
        
        //playerHand.addCard(Crazy8Game.yourDeck.drawCard());  //?
    }

    public void playCard(Card c) {
        // updates the value of the top of the discardPile, updates the value of discard,
        // and removes the card from player's hand.
        CrazyEightsGame.discard = c;
        CrazyEightsGame.discardPile.addCard(c);
        this.playerHand.dropCard(c);
        System.out.println(Colorize(this.name + " plays a " + c));
        System.out.println(Colorize(this.name + " has " + this.playerHand.getSize() + " card(s) in hand."));
        if (CrazyEightsGame.discard.getValue() == 8) { CrazyEightsGame.discard.setSuit(pickSuit()); }
    }

    protected String Colorize(String str) {
        return(this.playerColor + str + ANSI_RESET_COLOR);
    }

}
