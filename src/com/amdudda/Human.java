package com.amdudda;

import java.util.Scanner;

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

//    TODO: pick a card to play
    public Card pickCard() {
        Scanner s = CrazyEightsGame.scanner;
        // print out the player's info
        System.out.println(this);
        // and prompt for info:
        System.out.println("Choose a card to play:\n");
        int index = s.nextInt() - 1;
        s.nextLine();
        return this.playerHand.getHand().get(index);
    }
}
