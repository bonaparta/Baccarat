/**
 * The class <code>BaccaratRules</code> contains the ``Third-Card'' rules
 *    in the Baccarat card game.
 *
 * @author: Joseph W.H. Liu
 * @version: 1.00  03/05/00
 */

public class BaccaratRules {
	public static boolean isNaturalExist (int bankerValue, int playerValue) {
		return (bankerValue >= 8 || playerValue >= 8 )? true : false;
	}

	/**
		* Returns true if the player must draw a third card
		* @param  bankerValue   the banker's hand value (of first 2 cards)
		* @param  playerValue   the player's hand value (of first 2 cards)
	*/
	public static boolean doesPlayerDraw (int playerValue) {
		return ( playerValue <= 5 )? true : false;
	}

	/**
		* Returns true if the banker must draw a third card
		* @param  bankerValue            the banker's hand value (of first 2 cards)
		* @param  playerValue            the player's hand value (of first 2 cards)
		* @param  playerThirdCardValue   the player's third card value
		*             (if player has not drawn a third card, this value will
		*              not be used and can thus be set to any integer).
	*/
	public static boolean doesBankerDraw (int bankerValue,
			int playerThirdCardValue) {
		if (playerThirdCardValue == -1) {
			if (bankerValue <= 5)
				return true;
			return false;
		}
		if(bankerValue <= 6) {
			switch(bankerValue) {
			case 3:
				if(playerThirdCardValue == 8)
					return false;
				break;
			case 4:
				if(playerThirdCardValue >= 8 || playerThirdCardValue <= 1)
					return false;
				break;
			case 5:
				if(playerThirdCardValue >= 8 || playerThirdCardValue <= 3)
					return false;
				break;
			case 6:
				if(playerThirdCardValue >= 8 || playerThirdCardValue <= 5)
					return false;
				break;
			}
			return true;
		}
		return false;
	}
}