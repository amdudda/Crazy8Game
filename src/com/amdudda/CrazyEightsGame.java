package com.amdudda;

public class CrazyEightsGame {

    public static void main(String[] args) {
	// write your code here
        // Crazy Eights game

        // testing our three objects
        Deck gameDeck = new Deck();
        // print out the first few cards off the deck
        System.out.println(gameDeck.drawCard());
        System.out.println(gameDeck.drawCard());
        System.out.println(gameDeck.drawCard());

        Hand testHand = new Hand(gameDeck);

        System.out.println(testHand);
    }
}
