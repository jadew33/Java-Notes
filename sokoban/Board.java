package sokoban;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * To Do: -the storage site is moving with the box for * squares
 * 
 * A class that represents a Sokoban level board.
 * 
 * <p>
 * The class can read a Sokoban level from a plain text file where the following
 * symbols are used:
 * 
 * <ul>
 * <li>space is an empty square
 * <li># is a wall
 * <li>@ is the player
 * <li>$ is a box
 * <li>. is a storage location
 * <li>+ is the player on a storage location
 * <li>* is a box on a storage location
 * </ul>
 * 
 * <p>
 * The class manages a single {@code Player} object and a number of {@code Box},
 * {@code Wall}, and {@code Storage} objects. The class determines whether the
 * player can move to a specified square depending on the configuration of boxes
 * and walls.
 * 
 * <p>
 * The level is won when every box is moved to a storage location.
 * 
 * <p>
 * The class provides several methods that return information about a location
 * on the board .
 *
 */
public class Board {
	/*
	 * ADD SOME FIELDS HERE TO STORE THE WALLS, BOXES, AND STORAGE SITES
	 */

	private Player player;
	private int width;
	private int height;
	private ArrayList<Box> box = new ArrayList<Box>();
	private ArrayList<Wall> wall = new ArrayList<Wall>();
	private ArrayList<Storage> storage = new ArrayList<Storage>();

	/**
	 * Initialize a board of width 11 and height 11 with a {@code Player}
	 * located at (4, 5), a {@code Box} located at (5, 5), and a storage
	 * location located at (6, 5).
	 */
	public Board() {
		this.width = 11;
		this.height = 11;
		this.player = new Player(new Location(4, 5));
		this.box.add(new Box(new Location(5, 5)));
		this.storage.add(new Storage(new Location(6, 5)));
	}

	/**
	 * Initialize a board by reading a level from the file with the specified
	 * filename.
	 * 
	 * <p>
	 * The height of the board is determined by the number of lines in the file.
	 * The width of the board is determined by the longest line in the file
	 * where trailing spaces in a line are ignored.
	 * 
	 * @param filename the filename of the level
	 * @throws IOException if the level file cannot be read
	 */
	public Board(String filename) throws IOException {

		// call readLevel after initializing your fields
		this.readLevel(filename);

	}

	private final void readLevel(String filename) throws IOException {
		Path path = FileSystems.getDefault().getPath("src", "sokoban",
				filename);
		List<String> level = Files.readAllLines(path);
		this.height = level.size();
		this.width = 0;
		for (int y = 0; y < this.height; y++) {
			String row = level.get(y);
			if (row.length() > this.width) {
				this.width = row.length();
			}
			for (int x = 0; x < row.length(); x++) {
				// the location of this square
				Location loc = new Location(x, y);

				// the symbol at location (x, y)
				char c = row.charAt(x);
				if (c == ' ') {
					continue;
				} else if (c == '#') {
					// there is a wall here
					// make a Wall object and add it to your collection
					this.wall.add(new Wall(loc));

				} else if (c == '@') {
					Player p = new Player(loc);
					this.player = p;

				} else if (c == '$') {
					// there is a box here
					// make a Box object and add it to your collection
					this.box.add(new Box(loc));

				} else if (c == '.') {
					// there is a storage site here
					// make a Storage object and add it to your collection
					this.storage.add(new Storage(loc));

				} else if (c == '+') {
					Player p = new Player(loc);
					this.player = p;
					// there is also storage site here
					// make a Storage object and add it to your collection
					this.storage.add(new Storage(loc));

				} else if (c == '*') {
					// there is a box and a storage site here
					// make a Box object and a Storage object and add them
					// to your collections
					this.storage.add(new Storage(loc));
					this.box.add(new Box(loc));
				}
			}
		}
	}

	/**
	 * Returns the width of this board.
	 * 
	 * @return the width of this board
	 */
	public int width() {
		// ALREADY DONE FOR YOU
		return this.width;
	}

	/**
	 * Returns the height of this board.
	 * 
	 * @return the height of this board
	 */
	public int height() {
		// ALREADY DONE FOR YOU
		return this.height;
	}

	/**
	 * Returns a list of the walls in this board. The order of the walls is
	 * unspecified in the returned list.
	 * 
	 * @return a list of the walls in this board
	 */
	public List<Wall> getWalls() {
		return this.wall;
	}

	/**
	 * Returns a list of the boxes in this board. The order of the boxes is
	 * unspecified in the returned list.
	 * 
	 * @return a list of the boxes in this board
	 */
	public List<Box> getBoxes() {
		return this.box;
	}

	/**
	 * Returns a list of the storage locations in this board. The order of the
	 * storage locations is unspecified in the returned list.
	 * 
	 * @return a list of the storage locations in this board
	 */
	public List<Storage> getStorage() {
		return this.storage;
	}

	/**
	 * Returns the player.
	 * 
	 * @return the player
	 */
	public Player getPlayer() {
		// ALREADY DONE FOR YOU
		return this.player;
	}

	/**
	 * Returns the {@code Box} object located at the specified location on the
	 * board, or {@code null} if there is no such object.
	 * 
	 * @param loc a location
	 * @return the box object located at the specified location on the board, or
	 *         {@code null} if there is no such object
	 */
	public Box getBox(Location loc) {
		for (Box b : this.getBoxes()) {
			if (b.location().equals(loc))
				return b;
		}
		return null;
	}

	/**
	 * Returns {@code true} if there is a wall, player, or box at the specified
	 * location, {@code false} otherwise. Note that storage locations are
	 * considered unoccupied if there is no player or box on the location.
	 * 
	 * @param loc a location
	 * @return {@code true} if there is a wall, player, or box at the specified
	 *         location, {@code false} otherwise
	 */
	public boolean isOccupied(Location loc) {
		if (this.hasBox(loc) || this.hasWall(loc) || this.hasPlayer(loc))
			return true;
		return false;
	}

	/**
	 * Returns {@code true} if the specified location is unoccupied or has only
	 * a storage location, {@code false} otherwise
	 * 
	 * @param loc a location
	 * @return {@code true} if the specified location is unoccupied or has only
	 *         a storage location, {@code false} otherwise
	 */
	public boolean isFree(Location loc) {
		if (!this.isOccupied(loc) || this.hasStorage(loc))
			return true;
		return false;

	}

	/**
	 * Returns {@code true} if the specified location has a wall on it,
	 * {@code false} otherwise.
	 * 
	 * @param loc a location
	 * @return {@code true} if the specified location has a wall on it,
	 *         {@code false} otherwise
	 */
	public boolean hasWall(Location loc) {
		for (Wall w : this.getWalls()) {
			if (w.location().equals(loc))
				return true;
		}
		return false;
	}

	/**
	 * Returns {@code true} if the specified location has a box on it,
	 * {@code false} otherwise.
	 * 
	 * @param loc a location
	 * @return {@code true} if the specified location has a box on it,
	 *         {@code false} otherwise
	 */
	public boolean hasBox(Location loc) {
		for (Box b : this.getBoxes()) {
			if (b.location().equals(loc))
				return true;
		}
		return false;
	}

	/**
	 * Returns {@code true} if the specified location has a storage location on
	 * it, {@code false} otherwise.
	 * 
	 * @param loc a location
	 * @return {@code true} if the specified location has a storage location on
	 *         it, {@code false} otherwise
	 */
	public boolean hasStorage(Location loc) {
		for (Storage s : this.getStorage()) {
			if (s.location().equals(loc))
				return true;
		}
		return false;
	}

	/**
	 * Returns {@code true} if the specified location has a player on it,
	 * {@code false} otherwise.
	 * 
	 * @param loc a location
	 * @return {@code true} if the specified location has a player on it,
	 *         {@code false} otherwise
	 */
	public boolean hasPlayer(Location loc) {
		if (player.location().equals(loc))
			return true;
		return false;

	}

	/**
	 * Returns {@code true} if every storage location has a box on it,
	 * {@code false} otherwise.
	 * 
	 * @return {@code true} if every storage location has a box on it,
	 *         {@code false} otherwise
	 */
	public boolean isSolved() {
		int counter = 0;
		int number = this.getBoxes().size();
		for (Box b : this.getBoxes()) {
			for (Storage s : this.getStorage()) {
				if (b.location().equals(s.location()))
					counter++;
			}
		}
		if (counter == number)
			return true;
		return false;

	}

	/**
	 * Moves the player to the left adjacent location if possible. If there is a
	 * box in the left adjacent location then the box is pushed to the adjacent
	 * location left of the box.
	 * 
	 * <p>
	 * Returns {@code false} if the player cannot move to the left adjacent
	 * location (leaving the player location unchanged).
	 * 
	 * @return true if the player is moved to the left adjacent location, false
	 *         otherwise
	 */
	public boolean movePlayerLeft() {
		Location leftPos = new Location(new Location(player.location()).left());
		Location leftLeft = new Location(new Location(leftPos)).left();
		// False if there are 2 boxes where player is trying to go
		if (this.hasBox(leftPos) && this.hasBox(leftLeft))
			return false;
		if (this.isFree(leftPos) && !this.isOccupied(leftPos)
				// Even if there is a box, the player can still move if space
				// next to box is free
				|| (this.hasBox(leftPos) && this.isFree(leftLeft))
				// So that boxes can move even when they've been placed on
				// storage location
				|| (this.hasBox(leftPos) && this.hasStorage(leftPos)
						&& !this.hasWall(leftLeft))) {
			if (this.hasBox(leftPos))
				this.getBox(leftPos).moveLeft();
			this.player.moveLeft();
			return true;
		}
		return false;
	}

	/**
	 * Moves the player to the right adjacent location if possible. If there is
	 * a box in the right adjacent location then the box is pushed to the
	 * adjacent location right of the box.
	 * 
	 * <p>
	 * Returns {@code false} if the player cannot move to the right adjacent
	 * location (leaving the player location unchanged).
	 * 
	 * @return true if the player is moved to the right adjacent location, false
	 *         otherwise
	 */
	public boolean movePlayerRight() {
		Location rightPos = new Location(
				new Location(player.location()).right());
		Location rightRight = new Location(new Location(rightPos)).right();
		// False if there are 2 boxes where player is trying to go
		if ((this.hasBox(rightPos) && this.hasBox(rightRight)))
			return false;
		if (this.isFree(rightPos) && !this.isOccupied(rightPos)
				// Even if there is a box, the player can still move if space
				// next to box is free
				|| (this.hasBox(rightPos) && this.isFree(rightRight))
				// So that boxes can move even when they've been placed on
				// storage location
				|| (this.hasBox(rightPos) && this.hasStorage(rightPos))
						&& !this.hasWall(rightRight)) {
			if (this.hasBox(rightPos))
				this.getBox(rightPos).moveRight();
			this.player.moveRight();
			return true;
		}
		return false;
	}

	/**
	 * Moves the player to the above adjacent location if possible. If there is
	 * a box in the above adjacent location then the box is pushed to the
	 * adjacent location above the box.
	 * 
	 * <p>
	 * Returns {@code false} if the player cannot move to the above adjacent
	 * location (leaving the player location unchanged).
	 * 
	 * @return true if the player is moved to the above adjacent location, false
	 *         otherwise
	 */
	public boolean movePlayerUp() {
		Location upPos = new Location(new Location(player.location()).up());
		Location upUp = new Location(new Location(upPos)).up();
		// False if there are 2 boxes where player is trying to go
		if (this.hasBox(upPos) && this.hasBox(upUp))
			return false;
		if (this.isFree(upPos) && !this.isOccupied(upPos)
				// Even if there is a box, the player can still move if space
				// next to box is free
				|| (this.hasBox(upPos) && this.isFree(upUp))
				// So that boxes can move even when they've been placed on
				// storage location
				|| (this.hasBox(upPos) && this.hasStorage(upPos)
						&& !this.hasWall(upUp))) {
			if (this.hasBox(upPos))
				this.getBox(upPos).moveUp();
			this.player.moveUp();
			return true;
		}
		return false;
	}

	/**
	 * Moves the player to the below adjacent location if possible. If there is
	 * a box in the below adjacent location then the box is pushed to the
	 * adjacent location below of the box.
	 * 
	 * <p>
	 * Returns {@code false} if the player cannot move to the below adjacent
	 * location (leaving the player location unchanged).
	 * 
	 * @return true if the player is moved to the below adjacent location, false
	 *         otherwise
	 */
	public boolean movePlayerDown() {
		Location downPos = new Location(new Location(player.location()).down());
		Location downDown = new Location(new Location(downPos)).down();
		// False if there are 2 boxes where player is trying to go
		if ((this.hasBox(downPos) && this.hasBox(downDown)))
			return false;
		if (this.isFree(downPos) && !this.isOccupied(downPos)
				// Even if there is a box, the player can still move if space
				// next to box is free
				|| (this.hasBox(downPos) && this.isFree(downDown))
				// So that boxes can move even when they've been placed on
				// storage location
				|| (this.hasBox(downPos) && this.hasStorage(downPos))
						&& !this.hasWall(downDown)) {
			if (this.hasBox(downPos))
				this.getBox(downPos).moveDown();
			this.player.moveDown();
			return true;
		}
		return false;
	}

	/**
	 * Returns a string representation of this board. The string representation
	 * is identical to file format describing Sokoban levels.
	 * 
	 * @return a string representation of this board
	 */
	@Override
	public String toString() {
		// ALREADY DONE FOR YOU
		StringBuilder b = new StringBuilder();
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				Location loc = new Location(x, y);
				if (this.isFree(loc)) {
					if (this.hasStorage(loc) && !this.hasBox(loc)) {
						b.append(".");
					} else if (!this.hasStorage(loc)) {
						b.append(" ");
					}
				}
				if (this.hasWall(loc)) {
					b.append("#");
				}
				if (this.hasBox(loc)) {
					if (this.hasStorage(loc)) {
						b.append("*");
					} else {
						b.append("$");
					}
				}
				if (this.hasPlayer(loc)) {
					if (this.hasStorage(loc)) {
						b.append("+");
					} else {
						b.append("@");
					}
				}
			}
			b.append('\n');
		}
		return b.toString();
	}

	public static void main(String[] args) throws IOException {
		Board b = new Board();
		System.out.println(b);
	}
}
