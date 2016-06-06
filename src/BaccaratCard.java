public class BaccaratCard extends Card {
	public BaccaratCard(CardSuitEnum suit, CardRankEnum rank) {
		super(suit, rank);
	}

	@Override
	public int getValue() {
		if(getRank() == CardRankEnum.ACE)
			return 1;
		return (getRank().compareTo(CardRankEnum.CARD_10) >= 0)? 0 : getRank().ordinal() + 2;
	}
}