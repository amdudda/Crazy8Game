package com.amdudda;

import java.util.Random;

/**
 * Created by amdudda on 10/13/15.
 */
public class PC extends Player {
    // This facilitates automatic play by the computer.

    // constructor
    public PC() {
        // creates a computer player and deals them a myHand
        this.name = "The computer";
        this.playerHand = new Hand();
        this.playerColor = ANSI_BLUE;
    }

//    DONE: pick a card to play
    private Card pickCard(){
        // tells the computer what card to pick
        Card disc = CrazyEightsGame.card_in_play;

        // first try to find a numeric match
        for (Card c:this.playerHand.getMyHand()) {
            if (c.value !=8 && c.value == disc.getValue()) {
                return c;
            }
        }

        // then try to match the suit
        for (Card c:this.playerHand.getMyHand()) {
            if (c.value !=8 && c.suit.equals(disc.getSuit())) {
                return c;
            }
        }

        // okay, now let's check for eights
        for (Card c:this.playerHand.getMyHand()) {
            if (c.value == 8) { return c; }
        }

        // and if nothing was found, return fake values
        return new Card("fakesuit",-1);
    }

    // take a turn!
    public void takeTurn() {
        Card card = this.pickCard();
        Card disc = CrazyEightsGame.card_in_play;
        // so long the card picked is not a legal play and there are cards available to draw, draw a new card, then try to pick a card again
        while (!(card.isLegalToPlayOn(disc)) && CrazyEightsGame.gameDeck.getSize() > 0) {
            this.drawCard(CrazyEightsGame.gameDeck);
            System.out.println(Colorize(this.getName() + " draws a card."));
            card = this.pickCard();
        }
        // need logic to deal with if the draw my_pile has gone down to zero
        if (!(card.isLegalToPlayOn(disc)) && CrazyEightsGame.gameDeck.getSize() == 0) {
            // no way to play, and the computer passes
            System.out.println("No cards left in the deck.");
            System.out.println(Colorize(this.name + " passes."));
            return;
        } else {
            // once we have a legal play, play the card.
            this.playCard(card);
        }

    }

    public String pickSuit() {
        // picks a suit if an 8 is played
        String[] suits = {"Hearts","Diamonds","Clubs","Spades"};
        // pick a random number and pick that suit from the array and return it
        Random index = new Random();
        int i = index.nextInt(4);
        System.out.println(Colorize(this.name + " picks " + suits[i] + "."));
        return suits[i];
    }

}
