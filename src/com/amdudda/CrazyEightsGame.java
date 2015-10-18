package com.amdudda;

import java.util.ArrayList;
import java.util.Scanner;

public class CrazyEightsGame {

    public static Deck gameDeck;  // the game only has a single deck to draw from
    public static Pile discardPile; // the discard pile, in case we want to implement reshuffling.
    public static Card discard; // and only one card to worry about in actual gameplay - if an 8, may be different from top of discardPile.
    public static Scanner scanner;  // scanner for player input

    public static void main(String[] args) {
        // write your code here
        // Crazy Eights game

        // create our deck, scanner, and pop the card off gameDeck to create our current discarded card
        gameDeck = new Deck();
        discardPile = new Pile();
        discard = gameDeck.drawCard();
        // DONE: Implement superclass Pile so discard pile can be recycled in "reshuffling" versions of game.
        scanner = new Scanner(System.in);

        // set up our players - using an ArrayList simplifies adding multiple players
        ArrayList<Player> gamePlayers = new ArrayList<Player>();
        gamePlayers.add(new Human(getPlayerName()));
        gamePlayers.add(new PC());
        //gamePlayers.add(new PC());
        String keep_playing;

        /*
        outline of game play:
        1.  set up players - for now 1 PC and 1 Human
        2.  a player takes a turn
        3.  #2 repeats so long as two conditions are met: nobody's hand is empty and the deck is not empty.
         */


        // DONE: how to declare suit when an 8 is played?  reset the card's suit attribute during play?
        while (true) {
            System.out.println("Shuffling deck...");
            System.out.println("Dealing cards...");

            System.out.println("The top card of the deck is flipped over and play begins!");
            System.out.println("The current top of the discard pile is a " + discard + ".");
            while (!isGameOver(gamePlayers)) {
                for (Player p : gamePlayers) {
                    p.takeTurn();
                    if (isGameOver(gamePlayers)) {
                        break;
                    }
                } // end for each
            } // end while loop

            System.out.println("Game over!");

            // DONE: implement scoring
            reportRoundScores(gamePlayers);
            System.out.println("\nCurrent score for the game:");
            reportGameScores(gamePlayers, "is winning");

            System.out.println("\nWould you like to play another round (y/n)?");
            keep_playing = scanner.nextLine();
            // if they say no, stop the game.
            if (keep_playing.equals("n")) {
                break;
            } else {
                // if the game continues, we need to create a new deck and restart play.
                gameDeck = new Deck();
                discard = gameDeck.drawCard();
                for (Player p:gamePlayers) {
                    p.playerHand = new Hand();
                }
            }
        }

        //Report the final results of the game
        System.out.println("\nYou have chosen to end the game.");
        reportGameScores(gamePlayers, "won");
        System.out.println("\nThank you for playing!");

        // close the scanner when the game is over
        scanner.close();
    }

    private static boolean isGameOver(ArrayList<Player> gp) {
        if (gameDeck.getSize() == 0) {
            return true;
        }
        for (Player p : gp) {
            if (p.playerHand.getSize() == 0) {
                return true;
            }
        }
        return false;
    }

    private static int getScore(Player p) {
        // calculates the player's score for the round
            Hand pH = p.getPlayerHand();
            int roundScore = 0;
            if (pH.getSize() == 0) {
                return 0;
            } else {
                for (int i = 0; i < pH.getSize(); i++) {
                    roundScore += pH.getCardFromHand(i).getValue();
                }
                return roundScore;
            }
    }

    //TODO: Rework scoring so it can deal with multiple human players.
    private static void reportRoundScores(ArrayList<Player> gp){
        ArrayList<Integer> roundScores = new ArrayList<Integer>();
        Player p;
        int pScore, oldscore;
        System.out.println("\nScoring for this round:");
        for (int i=0; i<gp.size(); i++) {
            p = gp.get(i);
            pScore = getScore(p);
            roundScores.add(pScore);
            System.out.println(p.getName() + "'s score is: " + p.playerColor + pScore + " points." + Player.ANSI_RESET_COLOR);
            oldscore = gp.get(i).getScore();
            gp.get(i).setScore(oldscore + pScore);
        }
        // for now it's a 2-player game, so just do a simple comparison of scores
        if (roundScores.get(0) < roundScores.get(1)) {
            System.out.printf("%s wins the round!\n", gp.get(0).getName());
        } else if (roundScores.get(0) > roundScores.get(1)) {
            System.out.printf("%s wins the round!\n", gp.get(1).getName());
        } else {
            System.out.printf("The round is tied!\n");
        }
    }

    private static void reportGameScores(ArrayList<Player> gp, String scoretype) {
        //setScores(gp);
        int pScore;
        for (Player p : gp) {
            System.out.println(p.getName() + "'s score is: " + p.playerColor  + p.getScore() + " points." + Player.ANSI_RESET_COLOR);
        }
        if (gp.get(0).getScore() < gp.get(1).getScore()) {
            System.out.printf("%s %s the game!\n", gp.get(0).getName(), scoretype);
        } else if (gp.get(0).getScore() > gp.get(1).getScore()) {
            System.out.printf("%s %s the game!\n", gp.get(1).getName(), scoretype);
        } else {
            System.out.printf("The game is tied!\n", scoretype);
        }

    }

    private static String getPlayerName() {
        System.out.println("Please enter your name:");
        return scanner.nextLine();
    }
}
