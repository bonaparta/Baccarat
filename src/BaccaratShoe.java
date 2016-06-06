import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class BaccaratShoe extends Shoe {	
	private Random random;

	public BaccaratShoe() {
		this(new Random());
	}

	public BaccaratShoe(int decks) {
		this(new Random(), decks);
	}

	public BaccaratShoe(Random random) {
		this(random, 1);
	}

	public BaccaratShoe(Random random, int decks) {
		super(BaccaratCard.class, decks);
		this.random = random;
		Collections.shuffle(cards, random);
	}
}