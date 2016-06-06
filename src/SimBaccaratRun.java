public class SimBaccaratRun {
	public static void main(String[] args) {
		int n = (args.length <= 0) ? 5 : Integer.parseInt(args[0]);
		int[] times = new int[3];
		for(int i = 0; i < n; ++i) {
			Baccarat baccarat = new Baccarat(1);
			int whoWin = baccarat.deal();
			if(whoWin > 0)
				++times[0];
			else if(whoWin == 0)
				++times[1];
			else
				++times[2];
		}
		int sum = times[0] + times[1] + times[2];
		System.out.printf("fuck: %02f %02f %02f", (float)times[0] / sum, (float)times[1] / sum, (float)times[2] / sum);
	}
}