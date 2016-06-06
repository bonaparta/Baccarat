/**
 * The class <code>Baccarat</code> is to implement the Baccarat card game.
 */

public class Baccarat {
	// -----------------------------------------------------------------
	// class private instance variables
	// -----------------------------------------------------------------
	private BaccaratShoe shoe;           // deck for the Baccarat game
	private int reShufflePoint;      // re-shuffle point for the deck

	/**
		* default Baccarat constructor to use one pack of cards.
	*/
	public Baccarat() { this(1); }

	/**
		* Baccarat constructor to use p packs of cards.
	*/
	public Baccarat( int p ) {
		shoe = new BaccaratShoe(p);
		reShufflePoint = shoe.count() * 3 / 4;
	}

	public int deal() {
		return deal(shoe, new BaccaratHand(), new BaccaratHand());
	}
	/**
		* to play one game of Baccarat.  * It returns an integer value:
		*       a positive value - the banker wins,
		*       a negative value - the player wins,
		*       zero - a draw.
	*/
	public int deal(Shoe shoe, BaccaratHand banker, BaccaratHand player) {
		player.addCard(shoe.pop());
		banker.addCard(shoe.pop());
		player.addCard(shoe.pop());
		banker.addCard(shoe.pop());

		int playerValue = player.getValue();
		int bankerValue = banker.getValue();

		if (BaccaratRules.isNaturalExist(bankerValue, playerValue)) {
			return bankerValue - playerValue;
		}

		// ------------------------------------------------------
		// apply rule to see if the player draws a third card
		// ------------------------------------------------------
		int playerThirdCardValue = -1;
		if ( BaccaratRules.doesPlayerDraw(playerValue) ) {
			player.addCard(shoe.pop());
			playerValue = player.getValue();
			playerThirdCardValue = player.getCard(2).getValue();
		}

		// ------------------------------------------------------
		// apply rule to see if the banker draws a third card
		// ------------------------------------------------------
		if ( BaccaratRules.doesBankerDraw(bankerValue, playerThirdCardValue) ) {
			banker.addCard(shoe.pop());
			bankerValue = banker.getValue();
		}

		// ------------------------------------------------------
		// print details if necessary and return the value
		// difference between the banker's hand and the player's.
		// ------------------------------------------------------
		return bankerValue - playerValue;
	}
}