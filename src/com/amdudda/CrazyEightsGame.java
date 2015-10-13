package com.amdudda;

public class CrazyEightsGame {

    public static Deck gameDeck;  // the game only has a single deck to draw from
    public static Card discard; // and only one card to worry about in the discard pile

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
        // testing player and adding cards to player's hand.
        Human p1 = new Human("Riley");
        System.out.println(p1);
        p1.drawCard(gameDeck);
        System.out.println(p1);

        // TODO: decide where to evaluate whether a card is a legal play - whose job is that?
    }
}
