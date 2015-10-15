package com.amdudda;

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
        Card card = pickCard();
        Card disc = CrazyEightsGame.discard;
        // if the card is not a legal play, draw a new card, then check pick a card again
        if (!(card.isLegalToPlayOn(disc))) {
            drawCard(CrazyEightsGame.gameDeck);
        }

    }

}
