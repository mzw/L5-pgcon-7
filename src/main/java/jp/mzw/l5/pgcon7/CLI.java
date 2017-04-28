package jp.mzw.l5.pgcon7;

import java.io.IOException;

public class CLI {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			throw new IllegalArgumentException("Need to give path to file");
		}

		String pathToFile = args[0];
		int[] solved = new Solver(pathToFile).read().parse().solve();

		StringBuilder answer = new StringBuilder();
		String delim = "";
		int min = Integer.MAX_VALUE;
		for (int number : solved) {
			if (number < min) {
				min = number;
			}
			answer.append(delim).append(number);
			delim = " ";
		}
		System.out.println(min);
		System.out.println(answer);
	}
}
