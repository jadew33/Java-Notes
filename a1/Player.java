package a1;

/**
 * The player class represents a player of Zanzibar. Contains methods to access
 * and mutate variables for each player.
 * 
 * @implNote Comparable used to set ordering of players based on turn order
 * 
 * 
 * @author Jade Wei <br>
 *         Date: July 15, 2021 <br>
 *         Student Number: 20224398
 * 
 */
public class Player implements Comparable<Player> {

	/**
	 * Player class fields <br>
	 * {@code order} stores turn order of players<br>
	 * {@code points} stores the current points of a player<br>
	 * {@code chips} stores the current amount of chips each player has <br>
	 */
	private int order, points, chips;
	/**
	 * {@code isHuman} boolean value set to {@code true} if the player is human,
	 * with the default as {@code false} or computers
	 */
	private boolean isHuman;

	/**
	 * Constructor which initializes a new player with default starting values.
	 * 
	 * @param numChips sets starting amount of chips, either the default values
	 *                 or a custom value inputed by user, for each player
	 */
	protected Player(int numChips) {
		this.setChips(numChips);
		this.resetPoints();
		this.setIsHuman(false);
	}

	/**
	 * @return current turn order to reference the players
	 */
	protected int getOrder() {
		return order;
	}

	/**
	 * 
	 * @param order sets new turn order with {@code order + 1} to index turn
	 *              order from one
	 */
	protected void setOrder(int order) {
		this.order = order + 1;
	}

	/**
	 * @return current player points to compare point ranking among players
	 */
	protected int getPoints() {
		return points;
	}

	/**
	 * Sets points earned by player. Scoring methods Zanzibar, three of a kind,
	 * and sequence are arbitrarily assigned with the largest, middle, and least
	 * amount of points respectively. Points total will always result in the
	 * lowest amount of points and will only be updated if the points of the
	 * current roll is higher than the points of past rolls by the player in
	 * that round. The total points is calculated with a helper function,
	 * {@link sumPoints}.
	 * 
	 * @param scoringMethod tells method which scoring method is used to set
	 *                      points
	 * @param rolls         integer array storing the dice rolled to evaluate
	 */
	protected void setPoints(ScoringMethod scoringMethod, int[] rolls) {
		switch (scoringMethod) {
		case ZANZIBAR:
			this.points = 600;
			break;
		case THREEOFKIND:
			this.points = 500;
			break;
		case SEQUENCE:
			this.points = 400;
			break;
		case POINTSTOTAL:
			if (sumPoints(rolls) > points)
				this.points = sumPoints(rolls);
			break;
		}
	}

	/**
	 * In the case of a points total scoring method, takes in an integer array
	 * representing the dice rolls and scores them according to Zanzibar rules.
	 * 
	 * @param arr array of integers containing the rolled dice to sum
	 * @return total points for that array of rolls
	 */
	private static int sumPoints(int[] arr) {
		int score = 0;
		for (int i : arr) {
			if (i == 1)
				score += 100;
			else if (i == 6)
				score += 60;
			else
				score += i;
		}
		return score;
	}

	/**
	 * Used to check if any players have 0 or negative chips to declare a
	 * winner.
	 * 
	 * @return current chip amount
	 */
	protected int getChips() {
		return chips;
	}

	/**
	 * Updates chip amount of each player. All the players give the losing
	 * player 1, 2, 3, or 4 chips depending on if the highest outcome was a
	 * point total, sequence, three of a kind, or Zanzibar respectively.
	 * 
	 * @param updateAmount a negative or positive amount to be added to the
	 *                     current chip total
	 */
	protected void setChips(int updateAmount) {
		this.chips += updateAmount;
	}

	/**
	 * Customizes the output that applies to the human player and allows them to
	 * make decisions, such as how many times to reroll the dice, in the game.
	 * 
	 * @return {@code true} if the player is human and {@code false} if the
	 *         player is a computer
	 */
	protected boolean getIsHuman() {
		return isHuman;
	}

	/**
	 * Can be used to allow custom amount of human players in main Zanzibar game
	 * as an improvement.
	 * 
	 * @param isHuman takes in boolean value {@code true} if the player is human
	 *                and {@code false} if player is computer and updates the
	 *                current state of player
	 */
	protected void setIsHuman(boolean isHuman) {
		this.isHuman = isHuman;
	}

	/**
	 * This method resets temporary storage value of {@code points} for the next
	 * round to 0.
	 */
	protected void resetPoints() {
		this.points = 0;
	}

	/**
	 * @return string showing the player's current points and chips
	 */
	@Override
	public String toString() {
		return "Player: " + this.order + ", Points: " + this.points
				+ ", Chips: " + this.chips + (isHuman ? " You" : "");
	}

	/**
	 * @param p player object to compare with the turn order to help sort
	 *          ArrayList
	 * @return ascending order of players based on turn order
	 */
	public int compareTo(Player p) {
		int compareOrder = ((Player) p).getOrder();
		return this.order - compareOrder;
	}

}
