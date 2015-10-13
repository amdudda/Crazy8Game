package com.amdudda;

/**
 * Created by amdudda on 10/13/15.
 */
public class Human extends Player {
    // this is an extension of player to facilitate a human player
    // no new attributes yet

    // constructor
    public Human(String pName) {
        // creates a human player and deals them a hand
        this.name = pName;
        this.playerHand = new Hand();
    }

//    TODO: pick a card to play=
    public Card pickCard() {
        return null;
    }
}
