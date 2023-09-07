package gilad.karach.fullpath;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		System.out.println("Please insert your text");
		Scanner in = new Scanner(System.in);

		String text = in.nextLine();
		String textWithoutPunctuation = text.replaceAll("[^a-zA-Z ]", " ");
		String result = Arrays.stream(textWithoutPunctuation.split("\\s+"))
				.sorted(Main::compareWords)
				.collect(Collectors.joining(" "));
		System.out.println("The sorted text\n" + result);
	}

	private static int compareWords(String word1, String word2) {
		int word1Length = word1.length();
		int word2Length = word2.length();
		for (int i = 0; i < Math.min(word1Length, word2Length); i++) {
			HebLetter letter1 = getLetter(word1, i);
			HebLetter letter2 = getLetter(word2, i);
			if (!letter1.equals(letter2)) {
				return letter1.compareTo(letter2);
			}
		}
		return word1Length - word2Length;
	}

	private static HebLetter getLetter(String word, int position) {
		return HebLetter.valueOf(word.substring(position, position + 1).toUpperCase());
	}
}
