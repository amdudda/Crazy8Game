package com.amdudda;

import java.util.Scanner;

public class CrazyEightsGame {

    public static Deck gameDeck;  // the game only has a single deck to draw from
    public static Card discard; // and only one card to worry about in the discard pile
    public static Scanner scanner;  // scanner for player input

    public static void main(String[] args) {
	// write your code here
        // Crazy Eights game

        // testing our three objects
        gameDeck = new Deck();
        scanner = new Scanner(System.in);
        // print out the first few cards off the deck
        /*System.out.println(gameDeck.drawCard());
        System.out.println(gameDeck.drawCard());
        System.out.println(gameDeck.drawCard());

        Hand testHand = new Hand();
*/
        // testing player and adding cards to player's hand.
        Human p1 = new Human("Riley");

        // TODO: how to declare suit when an 8 is played?  reset the card's suit attribute during play?

        /*
        outline of game play:
        1.  set up players - for now 1 PC and 1 Human
        2.  a player takes a turn
        3.  #2 repeats so long as two conditions are met: nobody's hand is empty and the deck is not empty.
         */
        // close the scanner when you're done
        scanner.close();
    }
}
