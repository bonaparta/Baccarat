import java.util.stream.IntStream;

public class SimBaccaratProbabilityRun {
	private int[] cards = {32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
	private BaccaratHand banker, player;
	private enum BetEnum {BANKER, PLAYER, TIE, BANKER_PAIR, PLAYER_PAIR}
	private enum Status {P1, B1, P2, B2, P3, B3}
	private double[] probability = new double[BetEnum.values().length];
	private final double[] payTable = {0.95, 1, 8, 11, 11};

	public SimBaccaratProbabilityRun() {
		banker = new BaccaratHand();
		player = new BaccaratHand();
	}

	public void play(BaccaratHand banker, BaccaratHand player, double probability, Status status) {
		for(int i = 0 ; i < cards.length; ++i) {
			int value = i + 2;
			int numAllCard = IntStream.of(cards).sum();
			int numValueCard = cards[i];
			double prob = probability * numValueCard / numAllCard;
			cards[i] -= 1;
			switch(status) {
			case P1:
				player.addCard(new BaccaratCard(CardSuitEnum.CLUBS, CardRankEnum.values()[i]));
				play(banker, player, prob, Status.B1);
				player.removeCard(0);
				break;
			case B1:
				banker.addCard(new BaccaratCard(CardSuitEnum.CLUBS, CardRankEnum.values()[i]));
				play(banker, player, prob, Status.P2);
				banker.removeCard(0);
				break;
			case P2:
				player.addCard(new BaccaratCard(CardSuitEnum.CLUBS, CardRankEnum.values()[i]));
				play(banker, player, prob, Status.B2);
				player.removeCard(1);
				break;
			case B2:
				banker.addCard(new BaccaratCard(CardSuitEnum.CLUBS, CardRankEnum.values()[i]));
				if(BaccaratRules.isNaturalExist(banker.getValue(), player.getValue())) {
					win(banker, player, prob);
					banker.removeCard(1);
					break;
				}
				if(BaccaratRules.doesPlayerDraw(player.getValue())) {
					play(banker, player, prob, Status.P3);
					banker.removeCard(1);
					break;
				}
				if(BaccaratRules.doesBankerDraw(banker.getValue(), -1)) {
					play(banker, player, prob, Status.B3);
					banker.removeCard(1);
					break;
				}
				win(banker, player, prob);
				banker.removeCard(1);
				break;
			case P3:
				player.addCard(new BaccaratCard(CardSuitEnum.CLUBS, CardRankEnum.values()[i]));
				if(BaccaratRules.doesBankerDraw(banker.getValue(), value)) {
					play(banker, player, prob, Status.B3);
					player.removeCard(2);
					break;
				}
				win(banker, player, prob);
				player.removeCard(2);
				break;
			case B3:
				banker.addCard(new BaccaratCard(CardSuitEnum.CLUBS, CardRankEnum.values()[i]));
				win(banker, player, prob);
				banker.removeCard(2);
				break;
			}
			cards[i] += 1;
		}
	}

	private void win(BaccaratHand banker, BaccaratHand player, double probability) {
		int b = banker.getValue();
		int p = player.getValue();
		if(b > p) {
			this.probability[BetEnum.BANKER.ordinal()] += probability;
		} else if(b < p) {
			this.probability[BetEnum.PLAYER.ordinal()] += probability;
		} else {
			this.probability[2] += probability;
		}
		if(banker.getCard(0).getRank() == banker.getCard(1).getRank())
			this.probability[BetEnum.BANKER_PAIR.ordinal()] += probability;
		if(player.getCard(0).getRank() == player.getCard(1).getRank())
			this.probability[BetEnum.PLAYER_PAIR.ordinal()] += probability;
	}

	public static void main(String[] args) {
		System.out.println("Banker\tPlayer\tTie\tBanker Pair\tPlayer Pair");
		SimBaccaratProbabilityRun sbpr = new SimBaccaratProbabilityRun();
//		sbpr.player.addCard(new BaccaratCard(CardSuitEnum.CLUBS, CardRankEnum.CARD_9));
//		sbpr.play(sbpr.banker, sbpr.player, 1, Status.B1);
		sbpr.play(sbpr.banker, sbpr.player, 1, Status.P1);
		System.out.printf("%.12f %.12f %.12f %.12f %.12f\n",
				sbpr.probability[BetEnum.BANKER.ordinal()],
				sbpr.probability[BetEnum.PLAYER.ordinal()],
				sbpr.probability[BetEnum.TIE.ordinal()],
				sbpr.probability[BetEnum.BANKER_PAIR.ordinal()],
				sbpr.probability[BetEnum.PLAYER_PAIR.ordinal()]);
	}
}