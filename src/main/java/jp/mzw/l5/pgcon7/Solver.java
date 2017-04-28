package jp.mzw.l5.pgcon7;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Solver {

	private String pathToFile;
	private List<String> lines;
	private int n;
	private int[] sequence;

	public Solver(final String pathToFile) {
		if (pathToFile == null) {
			throw new IllegalArgumentException("Need to give path to file");
		}
		this.pathToFile = pathToFile;
		this.lines = null;
		this.n = -1;
		this.sequence = null;
	}

	public Solver read() throws IOException {
		File file = new File(this.pathToFile);
		if (!file.exists()) {
			throw new IllegalStateException("Not found: " + file.getAbsoluteFile());
		}
		this.lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
		return this;
	}

	public Solver parse() {
		if (this.lines == null) {
			throw new IllegalStateException("Read before parse");
		}
		switch (this.lines.size()) {
		case 0:
			throw new IllegalStateException("Not found both N and {}");
		case 1:
			throw new IllegalStateException("Not found {}");
		case 2:
			throw new IllegalStateException("Not found EOF");
		case 3:
			int n = Integer.parseInt(lines.get(0));
			String[] splits = lines.get(1).split(" ");
			int eof = Integer.parseInt(lines.get(2));
			if (n != splits.length) {
				throw new IllegalStateException("Not match N and #{}");
			}
			if (eof != 0) {
				throw new IllegalStateException("Illegal EOF");
			}
			this.n = n;
			this.sequence = Arrays.stream(splits).mapToInt(Integer::parseInt).toArray();
			return this;
		default:
			throw new IllegalStateException("Found unnecessary line(s)");
		}
	}

	public int[] solve() {
		int[] sequence = this.sequence;
		int[] calculated = new int[this.n];
		while (true) {
			for (int i = 0; i < sequence.length; i++) {
				int number = sequence[i];
				int frequency = 0;
				for (int appearance : sequence) {
					if (number == appearance) {
						frequency++;
					}
				}
				calculated[i] = frequency;
			}
			boolean fixed = true;
			for (int i = 0; i < sequence.length; i++) {
				if (sequence[i] != calculated[i]) {
					fixed = false;
					break;
				}
			}
			if (fixed) {
				break;
			}
			sequence = calculated;
			calculated = new int[this.n];
		}
		return sequence;
	}
}
