import java.util.Random;

public class Shuffler extends Shoe {
	private Random random;

	public Shuffler() {
		this(new Random());
	}

	public Shuffler(int decks) {
		this(new Random(), decks);
	}

	public Shuffler(Random random) {
		this(random, 1);
	}

	public Shuffler(Random random, int decks) {
		super(decks);
		this.random = random;
	}

	@Override
	public Card pop() {
		--count;
		return cards.remove(random.nextInt(count()));
	}
}