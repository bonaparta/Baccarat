
/*
    An object of type Hand represents a hand of cards.  The maximum number of
    cards in the hand can be specified in the constructor, but by default
    is 5.  A utility function is provided for computing the value of the
    hand in the game of Blackjack.
*/
import java.util.ArrayList;

public class BaccaratHand extends Hand {
	public int getValue() {
		int val = 0;      // The value computed for the hand.
		int cards = getCardCount();    // Number of cards in the hand.

		for ( int i = 0;  i < cards;  i++ ) {
			// Add the value of the i-th card in the hand.
			val += getCard(i).getValue();    // The i-th card;
		}
		return val % 10;
	}
}