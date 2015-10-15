package com.amdudda;

import java.util.Scanner;

/**
 * Created by amdudda on 10/13/15.
 */
public class Human extends Player {
    // this is an extension of player to facilitate a human player
    // no new attributes yet

    public static String menuoptions = "What would you like to do?\n1. Pick a card\n2. Draw a card";
        //\n3. Pass";  TODO: needs implemented

    // constructor
    public Human(String pName) {
        // creates a human player and deals them a hand
        this.name = pName;
        this.playerHand = new Hand();
    }

//    DONE: pick a card to play
    public Card pickCard() {
        Scanner s = CrazyEightsGame.scanner;
        Card card = chooseCard();
        Card disc = CrazyEightsGame.discard;
        String yn;
        while (!(card.isLegalToPlayOn(disc))) {
            // if the Human picks an invalid card, make them pick another one.
            System.out.println("The " + card + " cannot be played on the " + disc + ".");
            System.out.println("Would you like to pick a different card (y/n)?");
            yn = s.nextLine().toLowerCase();
            if ( yn.equals("n")) { card = chooseCard(); }
            else { break; }
        }
        return card;
    }

    private Card chooseCard() {
        // allows player to choose a card from their hand

        Scanner s = CrazyEightsGame.scanner;
        // print out the player's info
        System.out.println(this);
        // and prompt for info:
        System.out.println("Choose a card to play:");
        int index = s.nextInt() - 1;
        s.nextLine(); // move the scanner to the next line
        return this.playerHand.getHand().get(index);
    }

    // take a turn!
    public void takeTurn() {
        System.out.println(menuoptions);
        Scanner s = CrazyEightsGame.scanner;
        int selection = 0;
        /*
        Human turn's logic:
        1.  present turn options
        2.  if necessary, draw a card
        3.  pick a card to play
        4.  once card successfully played, end of turn
        */
        System.out.println(menuoptions);
        selection = s.nextInt();
        while (selection < 1 && selection > 2) {
            System.out.println("You have not made a valid choice.");
            System.out.println(menuoptions);
            selection = s.nextInt();
        }
        if (selection == 1) { this.pickCard(); }
        else if (selection == 2) { drawCard(CrazyEightsGame.gameDeck); }

    }

    public String pickSuit() {
        // lets user pick a suit when they play an 8.
        return "Hearts"; // fake data for now, we''l code this later once i've thought through logic a bit
    }
}
