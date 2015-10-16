package com.amdudda;

import java.util.ArrayList;
import java.util.Scanner;

public class CrazyEightsGame {

    public static Deck gameDeck;  // the game only has a single deck to draw from
    public static Card discard; // and only one card to worry about in the discard pile
    public static Scanner scanner;  // scanner for player input

    public static void main(String[] args) {
	// write your code here
        // Crazy Eights game

        // create our deck, scanner, and pop the card off gameDeck to create our current discarded card
        gameDeck = new Deck();
        scanner = new Scanner(System.in);
        discard = gameDeck.drawCard();
        // set up our players
        ArrayList<Player> gamePlayers = new ArrayList<Player>();
        gamePlayers.add(new PC());
        gamePlayers.add(new Human("Riley"));

        /*
        outline of game play:
        1.  set up players - for now 1 PC and 1 Human
        2.  a player takes a turn
        3.  #2 repeats so long as two conditions are met: nobody's hand is empty and the deck is not empty.
         */
        // DONE: how to declare suit when an 8 is played?  reset the card's suit attribute during play?
        System.out.println("The top card of the deck is flipped over and play begins!");
        System.out.println("The current top of the discard pile is a " + discard + ".");
        while (!isGameOver(gamePlayers)) {
            for (Player p:gamePlayers) {
                p.takeTurn();
                if (isGameOver(gamePlayers)) { break; }
                } // end for each
            } // end while loop

        System.out.println("Game over!");

        // TODO: implement scoring
        reportScores(gamePlayers);

        // close the scanner when the game is over
        scanner.close();
    }

    private static boolean isGameOver(ArrayList<Player> gp){
        if (gameDeck.getSize() == 0) { return true; }
        for (Player p:gp) {
            if (p.playerHand.getSize() == 0) { return true; }
        }
        return false;
    }

    private static void setScores(ArrayList<Player> gp) {
        // calculates the score
        for (Player p:gp){
            Hand pH = p.getPlayerHand();
            if (pH.getSize() == 0 ) { p.score += 0; }
            else {
                for (int i=0; i<pH.getSize(); i++) {
                    p.score += pH.getCardFromHand(i).getValue();
                }
            }
        }
    }

    private static void reportScores(ArrayList<Player> gp) {
        for (Player p:gp){
            System.out.println(p.getName() + "'s score is: " + p.getScore() + " + points.");
        }
        if (gp.get(0).getScore() < gp.get(1).getScore()) {
            System.out.println(gp.get(0).getName() + " wins!");
        } else if (gp.get(0).getScore() > gp.get(1).getScore()) {
            System.out.println(gp.get(1).getName() + " wins!");
        } else {
            System.out.println("The game is tied!");
        }

    }
}
