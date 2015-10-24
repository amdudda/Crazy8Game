package com.amdudda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class CrazyEightsGame {

    public static Deck gameDeck;  // the game only has a single deck to draw from
    public static Pile discardPile; // the discard pile, in case we want to implement reshuffling.
    public static Card discard; // and only one card to worry about in actual gameplay - if an 8, may be different from top of discardPile.
    public static Scanner scanner;  // scanner for player input

    public static void main(String[] args) {
        // write your code here
        // Crazy Eights game

        /*
        outline of game play:
        1.  initialize decks & set up players - for now 1 PC and 1 Human
        2.  a player takes a turn
        3.  #2 repeats so long as two conditions are met: nobody's hand is empty and the deck is not empty.
         */

        // set up our arraylist to store our players - using an ArrayList simplifies adding multiple players
        ArrayList<Player> gamePlayers = new ArrayList<Player>();
        // initialize the game
        initializeGame(gamePlayers);

        // and start playing the game
        playGame(gamePlayers);

        //Report the final results of the game
        System.out.println("\nYou have chosen to end the game.");
        reportGameScores(gamePlayers, "won");
        System.out.println("\nThank you for playing!");

        // close the scanner when the game is over
        scanner.close();
    }

    private static void initializeGame(ArrayList<Player> gP) {
        // create our deck, scanner, and pop the card off gameDeck to create our current discarded card
        gameDeck = new Deck();
        discardPile = new Pile();
        discard = gameDeck.drawCard();
        // DONE: Implement superclass Pile so discard my_pile can be recycled in "reshuffling" versions of game.
        scanner = new Scanner(System.in);

        // and set up our players
        gP.add(new Human(getPlayerName()));
        // gP.add(new Human("Player2")); // tested - game works for 3 players!  (Code below is wrong randomization for that, though.)
        gP.add(new PC());

        // randomize who goes first - use gp.size so we can update code to handle multiple players.
        Random r_num = new Random();
        int p_num = r_num.nextInt(gP.size());
        // two player game: if computer picked, move human player to end of arraylist
        if (p_num == 1) gP.add(gP.remove(0));
        // announce who goes first
        System.out.println(gP.get(0).getName() + " plays first!");

    }

    private static void playGame(ArrayList<Player> gamePlayers) {
        // this code is the beating heart of the game - players keep taking turns until human player decides to quit.
        String keep_playing;  // needed to facilitate exiting while-true loop
        while (true) {
            System.out.println("Shuffling deck...");
            System.out.println("Dealing cards...");

            System.out.println("The top card of the deck is flipped over and play begins!");
            System.out.println("The current top of the discard my_pile is a " + discard + ".");
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
            if (keep_playing.equalsIgnoreCase("n")) {
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


    // DONE: Rework scoring so it can deal with multiple human players.
    private static void reportRoundScores(ArrayList<Player> gp){
        int updatedScore;
        // grab the first player's score just to have an existing score to set minScore to.
        int minScore = (gp.get(0).getHandValue());

        for (Player p : gp) {
            // update player's score for the round
            p.setRoundScore(p.getHandValue());
            // need to keep track of what the lowest score is, for this round
            if (p.getRoundScore() < minScore) { minScore = p.getRoundScore(); }
            // print out the player's score for the round
            System.out.println(p.getName() + "'s score is: " + p.printRoundScore());
            // while we're at it, update the player's score for the game
            updatedScore = p.getRoundScore() + p.getScore();
            p.setScore(updatedScore);
        }

        int cur_player_score;
        String winnerlist = "";
        // then find the round winner(s)
        for (Player p : gp) {
            cur_player_score = p.getRoundScore();
            // if it's the first winner we've found, just add their name; otherwise precede it with & to create concatenation.
            if (cur_player_score == minScore && winnerlist.isEmpty()) {
                winnerlist = p.getName();
            } else if (cur_player_score == minScore) {
                winnerlist += " & " + p.getName();
            }
        }

        // and announce the winner(s)
        System.out.printf("%s wins the round!\n", winnerlist);

    }

    private static void reportGameScores(ArrayList<Player> gp, String scoretype) {
        int minScore = gp.get(0).getScore();  // need to seed lowest score with an exiting value
        String winnerlist = "";
        for (Player p : gp) {
            System.out.println(p.getName() + "'s score is: " + p.printGameScore());
            if (p.getScore() < minScore) { minScore = p.getScore(); }
        }

        // get a list of game winners now that we know what the lowest score is
        for (Player p : gp) {
            if (p.getScore() == minScore && winnerlist.isEmpty()) {
                winnerlist = p.getName();
            } else if (p.getScore() == minScore) {
                winnerlist += " & " + p.getName();
            }
        }

        // announce the winner(s)
        System.out.printf("%s %s the game!\n", winnerlist, scoretype);

    }

    private static String getPlayerName() {
        System.out.println("Please enter your name:");
        return scanner.nextLine();
    }
}
