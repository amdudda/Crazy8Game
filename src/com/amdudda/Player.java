package com.amdudda;

/**
 * Created by amdudda on 10/12/15.
 */
public class Player {
    // Player class, will split into polymorphic Human and Computer classes

    // attributes of all players - protected instead of private so sublcasses can access & inherit
    protected String name;
    protected Hand playerHand;

    // Constructor
    public Player(String pName) {
        // creates a player and deals them a hand
        this.name = pName;
        this.playerHand = new Hand();
    }
}
