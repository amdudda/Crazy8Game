package com.amdudda;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by amdudda on 10/13/15.
 */
public class Human extends Player {
    // this is an extension of player to facilitate a human player
    // no new attributes - they're all inherited

    // constructor
    public Human(String pName) {
        // creates a human player and deals them a myHand
        this.name = pName;
        this.playerHand = new Hand();
        this.playerColor = ANSI_YELLOW; // for now, all humans are yellow.
    }

    // take a turn!
    public void takeTurn() {
        Scanner s = CrazyEightsGame.scanner;
        int selection = 0;
        Card picked = new Card("fakesuit",-1);
        boolean valid_handindex, candraw;
        /*
        Human turn's logic:
        1.  list out myHand and add option 0 = draw a card
        2.  if player chooses a legal play, play the card
        3.  else if == 0, draw a card and loop
        4.  else loop and pick again
        4.  once card successfully played, end of turn
        */

        while (true) {
            // print out the player's hand so they can pick a card
            System.out.print(Colorize(this.toString()));
            candraw = (CrazyEightsGame.gameDeck.getSize() != 0);
            int index;

            // and prompt for a choice:
            if (candraw) {
                System.out.println("Choose a card to play or enter 0 to draw a card:");
            } else {
                System.out.println("Choose a card to play or enter 0 to pass (no cards left to draw).");
            }
            // DONE: error trapping for non-integer input
            while (true) {
                try {
                    index = s.nextInt() - 1;
                    break;
                } catch (InputMismatchException ioe) {
                    // alert the user and move the scanner to the next line.
                    System.out.println("Please enter a whole number.");
                    s.nextLine();
                }
            }
            s.nextLine(); // move the scanner to the next line
            valid_handindex = index >= 0 && index < this.playerHand.getSize();
            if (valid_handindex) picked = this.playerHand.getCardFromHand(index);

            // act on the user's choice
            if (index >= this.playerHand.getSize() || index < -1) {
                System.out.println("You have chosen an invalid option.  Please try again.");
            } else if (valid_handindex && !picked.isLegalToPlayOn(CrazyEightsGame.discard)) {
                // illegal play chosen, pick again
                System.out.println("The " + picked + " cannot be played on the " + CrazyEightsGame.discard + ".");
            } else if (valid_handindex && picked.isLegalToPlayOn(CrazyEightsGame.discard)) {
                // valid play picked, play it and break out of loop
                playCard(picked);
                break;
            } else if (index == -1 && candraw) {
                // draw a card
                this.drawCard(CrazyEightsGame.gameDeck);
                System.out.println("The top of the discard is still a " + CrazyEightsGame.discard + ".");
            } else {
                // presumably: (index == -1 && !candraw)
                // DONE: pass as a valid play.
                // user chose to pass because they have no legal plays and cannot draw.
                return;  // go back to calling method and do nothing.
            }   // end if-else
        } // end while
    } // end takeTurn

    public String pickSuit() {
        // lets user pick a suit when they play an 8.
        // return "Hearts"; // fake data for now, we''l code this later once i've thought through logic a bit
        Scanner s = CrazyEightsGame.scanner;
        String suit_picked;
        String[] suits = {"Hearts","Diamonds","Clubs","Spades"};
        int choice;
        while (true) {
            System.out.println("Pick a suit:");
            int i = 1;
            for (String suit : suits) {
                System.out.println(i + ".) " + suit);
                i++;
            }
            choice = s.nextInt() - 1;
            if (choice >=0 && choice < suits.length) { break; }  // can break out of loop if user makes valid selection
            System.out.println("Please make a valid selection.");
        }
        suit_picked = suits[choice];
        System.out.println(Colorize(this.name + " declares " + suit_picked));
        return suit_picked;
    }
}
