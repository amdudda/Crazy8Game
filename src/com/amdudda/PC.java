package com.amdudda;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by amdudda on 10/13/15.
 */
public class PC extends Player {
    // and this facilitates automatic play by the computer.

    // constructor
    public PC() {
        // creates a computer player and deals them a hand
        this.name = "The computer";
        this.playerHand = new Hand();
    }

//    DONE: pick a card to play
    public Card pickCard(){
        // tells the computer what card to pick
        Card disc = CrazyEightsGame.discard;

        // first try to find a numeric match
        for (Card c:this.playerHand.getHand()) {
            if (c.value !=8 && c.value == disc.getValue()) {
                return c;
            }
        }

        // then try to match the suit
        for (Card c:this.playerHand.getHand()) {
            if (c.value !=8 && c.suit.equals(disc.getSuit())) {
                return c;
            }
        }

        // okay, now let's check for eights
        for (Card c:this.playerHand.getHand()) {
            if (c.value == 8) { return c; }
        }

        // and if nothing was found, return fake values
        return new Card("fakesuit",-1);
    }

    // take a turn!
    public void takeTurn() {
        Card card = this.pickCard();
        Card disc = CrazyEightsGame.discard;
        int decksize = CrazyEightsGame.gameDeck.getSize();
        // so long the card picked is not a legal play, draw a new card, then try to pick a card again
        while (!(card.isLegalToPlayOn(disc)) && decksize > 0) {
            this.drawCard(CrazyEightsGame.gameDeck);
            card = this.pickCard();
        }
        // need logic to deal with if the draw pile has gone down to zero
        if (!(card.isLegalToPlayOn(disc)) && decksize == 0) {
            // no way to play, and the computer passes
            System.out.println(this.name + " passes.");
            return;
        } else {
            // once we have a legal play, play the card.
            this.playCard(card);
        }

        if (disc.getValue() == 8) { disc.setSuit(pickSuit()); }
    }

    public String pickSuit() {
        // picks a suit if an 8 is played
        String[] suits = {"Hearts","Diamonds","Clubs","Spades"};
        // pick a random number and pick that suit from the array and return it
        Random index = new Random();
        int i = index.nextInt(4);
        System.out.println(this.name + " picks " + suits[i] + ".");
        return suits[i];
    }

}
