package a1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * A class that models the game Zanzibar by initializing and running the game.
 * 
 * @author Jade Wei <br>
 *         Date: July 15, 2021 <br>
 *         Student Number: 20224398
 * 
 */

public class Zanzibar {
	private ArrayList<Player> players;
	Scanner scanner;

	/**
	 * Constructor creates a new instance of Zanzibar and enters game loop to
	 * play game {@code winners} is used to store the winner(s). Stored as an
	 * ArrayList to dynamically alter size depending on amount of winners.
	 * {@code StringBuilder sb} is then used to create output string of winners
	 * 
	 * <p>
	 * Enters first game loop which controls whether or not the user wants to
	 * play another game after the game has ended. A new ArrayList of
	 * {@code players} is created to store information pertaining to that
	 * instance of the game. The game is initialized and {@code winners} is
	 * populated when the main game loop is completed. The winners are then
	 * outputted and the user is asked if they would like to start another game.
	 * If the {@code response} entered by player is "y", another game will be
	 * started.
	 * </p>
	 */
	private Zanzibar() {
		scanner = new Scanner(System.in);
		ArrayList<Integer> winners;
		StringBuilder sb;
		String response = "";

		do {
			players = new ArrayList<Player>();
			winners = new ArrayList<Integer>();
			sb = new StringBuilder();

			System.out.println("Welome to Zanzibar!");
			initGame();
			winners = gameLoop(scanner, winners);

			for (int winner : winners) {
				// If it is not the last winner in the list, append comma
				sb.append(winner + (winner != winners.get(winners.size() - 1)
						&& winners.size() > 1 ? ", " : " "));
			}
			System.out.println((winners.size() > 1 ? "The winners are players: "
					: "The winner is player ") + sb.toString());

			response = printMessageReturnInput(
					"Enter Y to play another game or any other key to quit");
		} while (response.equals("y") ? true : false);
	}

	/**
	 * Main game loop of the game which runs until at least 1 player has 0
	 * chips. {@code outcomes} ArrayList of ScoringMethods stores the best
	 * outcome for each player to then determine the best outcome of that round.
	 * The boolean flag {@code reroll} will force a reroll if there are multiple
	 * losers or if everyone got the same outcome. The amount of
	 * {@code maxRolls} each player has per round is stored as an array so that
	 * the value can be modified.The highest outcome in {@code outcomes} is then
	 * calculated to determine how many chips every player gives to the losing
	 * player. The values are then reset for the next round and winners are
	 * identified if they exist. The main game loop will continue to run as long
	 * as {@code getGameOver()} returns false
	 * 
	 * @param scanner object to read user input
	 * @param winners to update the ArrayList of winners
	 * @return updated winners ArrayList
	 */
	private ArrayList<Integer> gameLoop(Scanner scanner,
			ArrayList<Integer> winners) {
		ArrayList<ScoringMethod> outcomes = new ArrayList<ScoringMethod>();
		boolean reroll = false;
		int[] maxRolls = new int[1];

		do {
			do {
				for (Player p : players)
					p.resetPoints();
				outcomes.clear();
				reroll = false;
				maxRolls[0] = 3;

				for (Player p : players) {
					System.out.println((p.getIsHuman() ? "Your"
							: ("Player " + p.getOrder() + "'s")) + " turn:");
					outcomes.add(rollDie(p, maxRolls));
				}

				// If there is a tie among all players or multiple losers in a
				// round, force a reroll
				// Convert to hashset, where all elements must be distinct
				// So if size of hashset <= 1, all elements are equal
				if ((new HashSet<ScoringMethod>(outcomes).size() <= 1
						&& findMax(outcomes) != ScoringMethod.POINTSTOTAL)
						|| findLoser() < 0) {
					reroll = true;
					printMessageReturnInput(findLoser() < 0
							? "Multiple people got the lowest score, "
									+ -findLoser()
									+ ". Press enter to restart round..."
							: ("Everybody got " + outcomes.get(0)
									+ ". Rerolling Dice..."));
				}
			} while (reroll);

			chipDistribution(outcomes);
			printPlayerStatus();
			printMessageReturnInput("Press enter to continue...");
		} while (!getGameOver(winners));
		return winners;
	}

	/**
	 * Rolls the die up to the maximum amount for each player and stores values
	 * in player object. The default maximum amount is 3, but the first player
	 * can choose to roll fewer times to limit the number of rolls their
	 * opponent can have.
	 * <p>
	 * 
	 * The rolls are stored in the {@code tempRoll} array. The points are saved
	 * to the player if is the best result.
	 * 
	 * @param p        represents a single player
	 * @param maxRolls is the maximum amount of rolls each player gets for that
	 *                 round. It is an array so that the value can be altered.
	 * @return the best scoring outcome a player got that round
	 */
	private ScoringMethod rollDie(Player p, int[] maxRolls) {
		int rolls = 0;
		int tempRoll[];
		String response = "";
		ScoringMethod scoringMethod = ScoringMethod.POINTSTOTAL;

		while (rolls < maxRolls[0] && !response.equals("n")) {
			tempRoll = new int[] { (int) (Math.random() * 6 + 1),
					(int) (Math.random() * 6 + 1),
					(int) (Math.random() * 6 + 1) };
			System.out.println(Arrays.toString(tempRoll) + " was rolled");

			if (findScoringMethod(tempRoll).getValue() >= scoringMethod
					.getValue())
				scoringMethod = findScoringMethod(tempRoll);
			p.setPoints(scoringMethod, tempRoll);

			rolls++;
			setDelay();
			if (p.getIsHuman() && rolls != maxRolls[0]) {
				// If first player decides to stop early, reset max rolls for
				// every player for that round
				response = printMessageReturnInput(
						"Press enter to roll again or N to stop").toLowerCase();
				if (response.equals("n") && p.getOrder() == 1)
					maxRolls[0] = rolls;
			}
		}
		return scoringMethod;
	}

	/**
	 * Finds the scoring method for an array of dice rolls. Zanzibar = 4, 5, 6
	 * and is the best outcome. Three of a kind is the next best outcome.
	 * Sequence = 1, 2, 3. If none of those outcomes are met, the roll is scored
	 * by summing the points
	 * 
	 * @param rolled the array of dice roll to evaluate the scoring method
	 * @return the scoring method of that array of rolls
	 */

	private ScoringMethod findScoringMethod(int[] rolled) {
		if (searchArray(rolled, 4) && searchArray(rolled, 5)
				&& searchArray(rolled, 6))
			return ScoringMethod.ZANZIBAR;
		if (rolled[0] == rolled[1] && rolled[1] == rolled[2])
			return ScoringMethod.THREEOFKIND;
		if (searchArray(rolled, 1) && searchArray(rolled, 2)
				&& searchArray(rolled, 3))
			return ScoringMethod.SEQUENCE;
		return ScoringMethod.POINTSTOTAL;
	}

	/**
	 * Reusable search method for a number array to see if there is a match.
	 * Used in Zanzibar to determine scoring method.
	 * 
	 * @param arr    to iterate and search through
	 * @param target value to see if there is a match in the array
	 * @return {@code true} if there is a match in the array and {@code false}
	 *         if not
	 */
	private boolean searchArray(int[] arr, int target) {
		for (int i : arr)
			if (i == target)
				return true;
		return false;
	}

	/**
	 * Updates amount of chips for each player by redistributing chips to losing
	 * player. Highest outcome {@code outcome} of the round is calculated and
	 * stored to determine how many chips are given to the losing player. 1, 2,
	 * 3, and 4 chips are given for points total, sequence, three of a kind, and
	 * Zanzibar respectively, stored in {@code chips} The player with the least
	 * points is designated the loser.
	 * 
	 * @param outcomes ArrayList storing the bset outcomes of each player to
	 *                 determine how many chips are redistributed
	 */
	private void chipDistribution(ArrayList<ScoringMethod> outcomes) {
		String category = "";
		ScoringMethod outcome = findMax(outcomes);
		int loser = findLoser(), chips = outcome.getValue();

		switch (outcome) {
		case POINTSTOTAL:
			category = "Point Score";
			break;
		case SEQUENCE:
			category = "Sequence";
			break;
		case THREEOFKIND:
			category = "Three of a Kind";
			break;
		case ZANZIBAR:
			category = "Zanzibar";
			break;
		}

		for (Player p : players) {
			p.setChips((p.getOrder() != loser) ? -chips
					: chips * (players.size() - 1));
		}

		System.out
				.println("The winning outcome for this round was: " + category);
		System.out.println("The loser of the round, player " + loser + ", gets "
				+ chips + (chips > 1 ? " chips" : " chip") + " from everyone.");
	}

	/**
	 * Find the highest outcome of a round, stored in, comparing the values
	 * assigned to each ScoringMethod
	 * 
	 * @param outcomes to search through to find the maximum value
	 * @return the highest value scoring method
	 */
	private ScoringMethod findMax(ArrayList<ScoringMethod> outcomes) {
		ScoringMethod max = ScoringMethod.POINTSTOTAL;
		for (int i = 0; i < outcomes.size(); i++) {
			if (outcomes.get(i).getValue() > max.getValue()) {
				max = outcomes.get(i);
			}
		}
		return max;
	}

	/**
	 * Finds the player with the least amounts of points
	 * 
	 * @return the player with the lowest amount as the losing player or a
	 *         negative number if there happens to be multiple losers in a case
	 *         of point total
	 */
	private int findLoser() {
		int points = 1000;
		int loserIndex = -1;
		for (Player p : players) {
			if (p.getPoints() < points) {
				points = p.getPoints();
				loserIndex = p.getOrder();
			} else if (p.getPoints() == points && points < 400)
				return -points;
		}
		return loserIndex;
	}

	/**
	 * Checks whether or not the game is over based on if a player got rid of
	 * all their chips and won
	 * 
	 * @param winners ArrayList to iterate through each round to see if any
	 *                players have gotten rid of their chips.
	 * @return {@code true} if a player has 0 or fewer chips and {@code false}
	 *         otherwise
	 */
	private boolean getGameOver(ArrayList<Integer> winners) {
		for (Player p : players) {
			if (p.getChips() <= 0)
				winners.add(p.getOrder());
		}
		return winners.size() > 0;
	}

	/**
	 * Initializes a new game of Zanzibar. The number of players are created
	 * based on user input. Each player is then either assigned a default value
	 * of chips, 15 for 2 players or 20 for 3 or more, or a custom amount
	 * entered by the user. A coin toss is then used to determine the turn
	 * order. The data is stored in a HashMap {@code coinToss} which maps
	 * players to a boolean value true for heads and false for tails. If
	 * multiple people got heads, a tiebreaker will be performed among those
	 * players to determine the resulting turn order.
	 * 
	 */
	private void initGame() {
		scanner = new Scanner(System.in);
		players = new ArrayList<Player>();
		Map<Player, Boolean> coinToss = new HashMap<Player, Boolean>();
		String response = "";

		do {
			response = printMessageReturnInput(
					"Enter in the number of players");
		} while (!isInt(response));
		int numPlayers = Integer.parseInt(response);

		int numChips = numPlayers > 2 ? 20 : 15;
		String customChips = printMessageReturnInput("By default, you get "
				+ numChips
				+ " chips. Hit enter to proceed or enter a custom number instead");
		if (!customChips.isEmpty() && isInt(customChips))
			numChips = Integer.parseInt(customChips);

		for (int i = 0; i < numPlayers; i++) {
			players.add(new Player(numChips));
			coinToss.put(players.get(i), true);
		}

		// Implements 1 human player and the remaining as computers
		players.get(0).setIsHuman(true);

		// 0 assigned to tails; 1 assigned to heads
		printMessageReturnInput(
				"Toss a coin to determine turn order for the game. Press enter to continue...");
		Set<Player> tieContenders = getKeys(coinToss, true);
		do {
			for (Player p : tieContenders) {
				coinToss.replace(p, (int) (Math.random() * 2) == 1);
				if (tieContenders.size() > 1)
					System.out.println(
							(p.getIsHuman() ? "You got " : "Computer got ")
									+ (coinToss.get(p) ? "heads" : "tails"));
				setDelay();
			}

			tieContenders = getKeys(coinToss, true);
			if (tieContenders.size() == 0) {
				System.out.println("Everyone got tails. Retossing the coin...");
				tieContenders = coinToss.keySet();
				continue;
			} else if (tieContenders.size() > 1)
				System.out.println(
						"Multiple people got heads. Doing a tiebreaker...");

			// Set order of players if they do not already have an order
			for (Player p : players) {
				if (!tieContenders.contains(p) && p.getOrder() == 0) {
					numPlayers--;
					p.setOrder(numPlayers);
					coinToss.remove(p);
				}
			}
		} while (tieContenders.size() != 1);
		printMessageReturnInput(
				"Results calculated. Press enter to continue...");

		// Must set first player index to 1 ( + 1 added in p.setOrder())
		for (Player p : players) {
			if (p.getOrder() == 0) {
				p.setOrder(0);
				System.out.println(
						p.getIsHuman() ? "You start!" : "Computer Starts!");
			}
		}

		// Reorders players based on turn order
		Collections.sort(players);
		printPlayerStatus();
	}

	/**
	 * Code taken from https://mkyong.com/java/java-get-keys-from-value-hashmap/
	 * <br>
	 * Used in Zanzibar to return a set of players (keys) whose value matches
	 * the boolean param
	 * 
	 * @param map   of players mapped to {@code true} (tossed heads) or
	 *              {@code false} (tossed tails) to search through to find out
	 *              which players got what result
	 * @param value {@code true} if returning a set of players who tossed heads,
	 *              {@code false} if returning a set of players who tossed tails
	 * @return a set of keys, players, whose values, boolean true or false,
	 *         match the param
	 */
	private static Set<Player> getKeys(Map<Player, Boolean> map,
			boolean value) {
		Set<Player> result = new HashSet<>();
		if (map.containsValue(value))
			for (Map.Entry<Player, Boolean> entry : map.entrySet())
				if (Objects.equals(entry.getValue(), value))
					result.add(entry.getKey());
		return result;
	}

	/**
	 * Sets a 1 second delay to improve readability of output.
	 */
	private void setDelay() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Used in Zanzibar to keep user in a loop if they input something anything
	 * other than an integer when an integer is expected.
	 * 
	 * @param num String storing user input to try and convert to integer
	 * @return {@code true} if input is integer, {@code false} if input is not
	 */
	private boolean isInt(String num) {
		try {
			Integer.parseInt(num);
		} catch (Exception e) {
			System.out.println("ERROR! Must enter valid integer");
			return false;
		}
		return true;
	}

	/**
	 * Prints out message passed in as param and returns user's response
	 * 
	 * @param message to output to user
	 * @return user input to store and use locally
	 */

	private String printMessageReturnInput(String message) {
		System.out.println(message);
		return scanner.nextLine().toLowerCase();
	}

	/**
	 * Print out the status (chips, points) of each player.
	 */
	private void printPlayerStatus() {
		for (Player p : players) {
			System.out.println(p.toString());
			setDelay();
		}
	}

	/**
	 * Main method that creates a new Zanzibar instance to start game
	 */
	public static void main(String[] args) {
		new Zanzibar();
	}

}
