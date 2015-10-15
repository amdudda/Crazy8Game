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
        //System.out.println(p1);
        p1.drawCard(gameDeck);
        System.out.println(p1);

        System.out.println("Pick a a card...");
        System.out.println("You have picked a " + p1.pickCard());
        // DONE: decide where to evaluate whether a card is a legal play - whose job is that?
        // TODO: how to declare suit when an 8 is played?  reset the card's suit attribute during play?

        // close the scanner when you're done
        scanner.close();
    }
}
