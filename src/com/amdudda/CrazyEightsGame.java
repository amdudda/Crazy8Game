package com.amdudda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CrazyEightsGame {

    public static Deck gameDeck;  // the game only has a single deck to draw from
    public static Pile discardPile; // the discard my_pile, in case we want to implement reshuffling.
    public static Card discard; // and only one card to worry about in actual gameplay - if an 8, may be different from top of discardPile.
    public static Scanner scanner;  // scanner for player input

    public static void main(String[] args) {
        // write your code here
        // Crazy Eights game

        // create our deck, scanner, and pop the card off gameDeck to create our current discarded card
        gameDeck = new Deck();
        discardPile = new Pile();
        discard = gameDeck.drawCard();
        // DONE: Implement superclass Pile so discard my_pile can be recycled in "reshuffling" versions of game.
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

    // DONE: Rework scoring so it can deal with multiple human players.
    private static void reportRoundScores(ArrayList<Player> gp){
        HashMap<Player,Integer> roundScores = new HashMap<Player,Integer>();
        int roundScore, updatedScore;
        // grab the first player's score just to have an existing score to set minScore to.
        int minScore = getScore(gp.get(0));
        // build up our hashmap of players and their scores for this round
        for (Player p : gp) {
            roundScore = getScore(p);
            roundScores.put(p,roundScore);
            // need to keep track of what the lowest score is, for this round
            if (roundScore < minScore) { roundScore = minScore; }
            // print out the player's score for the round
            System.out.println(p.getName() + "'s score is: " + p.playerColor + roundScore + " points." + Player.ANSI_RESET_COLOR);
            // while we're at it, update the player's score for the game
            updatedScore = roundScore + p.getScore();
            p.setScore(updatedScore);
        }

        int cur_player_score;
        String winnerlist = "";
        // then find the round winner(s)
        for (Player p : gp) {
            cur_player_score = roundScores.get(p);
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
            System.out.println(p.getName() + "'s score is: " + p.playerColor  + p.getScore() + " points." + Player.ANSI_RESET_COLOR);
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
