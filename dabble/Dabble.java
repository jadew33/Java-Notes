package dabble;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import dict.Dictionary;

/**
 * A class that models the state of the puzzle game Dabble.
 * 
 * <p>
 * The game Dabble is made up of five English words of lengths 2, 3, 4, 5, and 6
 * (these five words make up a solution for the puzzle). The letters of the all
 * of the words are randomly scrambled together to produce five scrambled puzzle
 * words of lengths 2, 3, 4, 5, and 6. For example, the five solution words:
 * 
 * <p>
 * {@code it, you, here, batch, burner}
 * 
 * <p>
 * might produce the five scrambled words:
 * 
 * <p>
 * {@code un, uei, ytrc, bahrt, obrehe}
 * 
 * <p>
 * A player attempts to unscramble the words by repeatedly exchanging a letter
 * from one scrambled word with the letter in a second scrambled word.
 * 
 * <p>
 * The puzzle is solved when the player has formed five English words of lengths
 * 2, 3, 4, 5, and 6 (not necessarily the same words as the original solution
 * because there is often multiple solutions).
 *
 */
public class Dabble {

	private Map<Integer, String> solution;
	private Map<Integer, String> scrambled;

	/**
	 * The dictionary used by the class.
	 */
	public static final Dictionary DICT = new Dictionary();

	/**
	 * The shortest word length in the game.
	 */
	public static final int MIN_WORD_LENGTH = 2;

	/**
	 * The longest word length in the game.
	 */
	public static final int MAX_WORD_LENGTH = 6;

	/**
	 * The number of words in the game.
	 */
	public static final int NUMBER_OF_WORDS = 5;

	/**
	 * Initializes this dabble to a specific set of scrambled and solution words
	 * for debugging and testing purposes.
	 * 
	 * @param notUsed not used, included so that this constructor would have a
	 *                different signature than the other constructors
	 */
	public Dabble(int notUsed) {
		this.solution = new TreeMap<>();
		this.scrambled = new TreeMap<>();

		this.solution.put(2, "ad");
		this.solution.put(3, "bet");
		this.solution.put(4, "cook");
		this.solution.put(5, "dumps");
		this.solution.put(6, "eclair");

		this.scrambled.put(2, "ri");
		this.scrambled.put(3, "alc");
		this.scrambled.put(4, "espm");
		this.scrambled.put(5, "udkoo");
		this.scrambled.put(6, "ctebad");
	}

	/**
	 * Initialize the words of the game by choosing random words from a
	 * dictionary.
	 */
	public Dabble() {
		this.solution = new TreeMap<>();
		this.scrambled = new TreeMap<>();

		// Temporary hold for list of words in dictionary containing i letters
		List<String> wordList = new ArrayList<String>();

		// Gets a random word containing i letters and adds to solution list
		for (int i = MIN_WORD_LENGTH; i <= MAX_WORD_LENGTH; i++) {
			wordList = DICT.getWordsByLength(i);
			this.solution.put(i,
					wordList.get((int) (Math.random() * wordList.size())));
			wordList.clear();
		}

		// Creates deep copy of the solution to scramble
		Map<Integer, String> solutionCopy = new TreeMap<Integer, String>();
		solutionCopy.putAll(this.solution);

		// Calls shuffle method to create new scrambled words
		this.scrambled = shuffle(solutionCopy);
	}

	/**
	 * Initialize the words of the game by using the specified words.
	 * 
	 * <p>
	 * There must be exactly {@code NUMBER_OF_WORDS} strings in the
	 * {@code words} otherwise an exception is thrown. Furthermore, the strings
	 * must be in ascending order of length from
	 * {@code MIN_WORD_LENGTH, MIN_WORD_LENGTH + 1,
	 * MIN_WORD_LENGTH + 2, ... , MAX_WORD_LENGTH}. Finally, the strings must
	 * all be contained in the dictionary used by the class.
	 * 
	 * @param words an array of NUMBER_OF_WORDS strings in ascending order of
	 *              length
	 * @throws IllegalArgumentException if
	 *                                  {@code words.length != Dabble.NUMBER_OF_WORDS}
	 *                                  or if the strings in word are not in
	 *                                  ascending order by length
	 */
	public Dabble(String... words) {
		this.solution = new TreeMap<>();
		this.scrambled = new TreeMap<>();

		// Puts specified words into solution list
		int key = MIN_WORD_LENGTH;
		for (String s : words) {
			this.solution.put(key, s);
			key++;
		}

		// Creates copy of solution set to scramble and creates scrambled list
		Map<Integer, String> solutionCopy = new TreeMap<Integer, String>();
		solutionCopy.putAll(this.solution);
		this.scrambled = shuffle(solutionCopy);

	}

	/**
	 * Method to create and return scrambled dabble words
	 * 
	 * @param words map of words that will be scrambled
	 */
	public Map<Integer, String> shuffle(Map<Integer, String> words) {

		// Creates a list of char to store and shuffle letters
		List<Character> letters = new ArrayList<Character>();
		for (int i = MIN_WORD_LENGTH; i <= MAX_WORD_LENGTH; i++) {
			for (int j = 0; j < words.get(i).length(); j++) {
				letters.add(words.get(i).charAt(j));
			}
		}
		Collections.shuffle(letters);

		String word = ""; // Temporary hold for shuffled word
		int randIndex = 0; // Temporary index to get from letters list

		// Gets a random index to retrieve value from letters i times and then
		// removes the letter at index to create scrambled words
		for (int i = MIN_WORD_LENGTH; i <= MAX_WORD_LENGTH; i++) {
			for (int j = 0; j < i; j++) {
				randIndex = (int) (Math.random() * letters.size());
				word += letters.get(randIndex);
				letters.remove(randIndex);
			}
			words.put(i, word); // To put in scrambled list
			word = "";
		}

		return words;
	}

	/**
	 * Returns a string representation of the puzzle.
	 * 
	 * <p>
	 * The returned string consists of each scrambled word separated by a comma
	 * and space, followed by space-colon-space, followed by each solution word
	 * separated by a comma and space.
	 * 
	 * @return a string representation of the puzzle
	 */
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder(
				this.scrambled.get(Dabble.MIN_WORD_LENGTH));
		for (int len = Dabble.MIN_WORD_LENGTH
				+ 1; len <= Dabble.MAX_WORD_LENGTH; len++) {
			b.append(", ");
			b.append(this.scrambled.get(len));
		}
		b.append(" : ");
		b.append(this.solution.get(Dabble.MIN_WORD_LENGTH));
		for (int len = Dabble.MIN_WORD_LENGTH
				+ 1; len <= Dabble.MAX_WORD_LENGTH; len++) {
			b.append(", ");
			b.append(this.solution.get(len));
		}
		return b.toString();
	}

	/**
	 * Returns {@code true} if each scrambled word is contained in the
	 * dictionary used by the class, {@code false} otherwise.
	 * 
	 * <p>
	 * It is not the case that the scrambled words must be equal to the solution
	 * words because it is possible that many different solutions exist for any
	 * given puzzle.
	 * 
	 * @return {@code true} if each scrambled word is contained in the
	 *         dictionary used by the class, {@code false} otherwise.
	 */
	public boolean isSolved() {
		if (DICT.contains(this.scrambled.get(2))
				&& DICT.contains(scrambled.get(3))
				&& DICT.contains(this.scrambled.get(4))
				&& DICT.contains(this.scrambled.get(5))
				&& DICT.contains(this.scrambled.get(6)))
			return true;
		return false;
	}

	/**
	 * Exchange a letter in one scrambled word with a letter from a second
	 * scrambled word. The two scrambled words might be the same word, in which
	 * case two letters are exchanged in the same word.
	 * 
	 * <p>
	 * The letter having {@code index1} in the scrambled word having length
	 * {@code len1} is exchanged with the letter having {@code index2} in the
	 * scrambled word having length {@code len2}.
	 * 
	 * <p>
	 * Consider the dabble {@code d} whose string representation is equal to:
	 * 
	 * <p>
	 * {@code "eb, ueu, eyoh, rnhti, rrtacb : it, you, here, batch, burner"}
	 * 
	 * <p>
	 * Then {@code d.exchange(2, 0, 5, 4)} would exchange the first letter of
	 * {@code "eb"} with the last letter of {@code "rnhti"}, and
	 * {@code d.toString()} would return the string equal to:
	 * 
	 * <p>
	 * {@code "ib, ueu, eyoh, rnhtb, rrtacb : it, you, here, batch, burner"}
	 * 
	 * @param len1   the length of the first word
	 * @param index1 the index of the letter to exchange of the first word
	 * @param len2   the length of the second word
	 * @param index2 the index of the letter to exchange of the second word
	 * @throws IllegalArgumentException if {@code len1} or {@code len2} are not
	 *                                  valid Dabble word lengths, or if
	 *                                  {@code index1} or {@code index2} are not
	 *                                  valid indexes for their respective
	 *                                  strings
	 */
	public void exchange(int len1, int index1, int len2, int index2) {

		// If the switch occurs within same scrambled word
		if (len1 == len2) {
			StringBuilder b = new StringBuilder(this.scrambled.get(len1));
			char c = b.charAt(index2);
			b.setCharAt(index2, b.charAt(index1));
			b.setCharAt(index1, c);
			this.scrambled.replace(len2, b.toString());
		}

		// To swap letters of different scrambled words
		else {
			StringBuilder b1 = new StringBuilder(this.scrambled.get(len1)),
					b2 = new StringBuilder(this.scrambled.get(len2));
			char c = b1.charAt(index1), c2 = b2.charAt(index2);
			b1.setCharAt(index1, c2);
			b2.setCharAt(index2, c);
			this.scrambled.replace(len1, b1.toString());
			this.scrambled.replace(len2, b2.toString());
		}
	}

	/**
	 * Returns the map of scrambled words.
	 * 
	 * <p>
	 * The returned map maps the word length to a scrambled word.
	 * 
	 * @return the map of scrambled words
	 */
	public Map<Integer, String> getScrambledWords() {
		// ALREADY DONE FOR YOU
		return this.scrambled;
	}

	/**
	 * Returns a map of solution words. More than one solution may exist; this
	 * method always returns the solution that was used to generate the puzzle.
	 * 
	 * <p>
	 * The returned map maps the word length to a solution word.
	 * 
	 * @return the map of solution words
	 */
	public Map<Integer, String> getSolutionWords() {
		// ALREADY DONE FOR YOU
		return this.solution;
	}

	public static void main(String[] args) {
		Dabble dab = new Dabble();
		System.out.println(dab);
	}
}
