package com.amdudda;

public class CrazyEightsGame {

    public static Deck gameDeck;

    public static void main(String[] args) {
	// write your code here
        // Crazy Eights game

        // testing our three objects
        gameDeck = new Deck();
        // print out the first few cards off the deck
        /*System.out.println(gameDeck.drawCard());
        System.out.println(gameDeck.drawCard());
        System.out.println(gameDeck.drawCard());

        Hand testHand = new Hand();
*/
        Player p1 = new Player("Riley");
        System.out.println(p1);
    }
}
