package spring24.week9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * A Sudoku puzzle is a 9x9 grid of numbers, where each row, column, and 3x3
 * subgrid contains the numbers 1-9 exactly once.
 * 
 * The goal of this challenge is to write a program that can solve Sudoku
 * puzzles.
 * 
 * Given an input .sdku file (a plain-text file containing the initial state of
 * a Sudoku puzzle), find the solution to the puzzle and write the solution to a
 * new .sdku file.
 * 
 * The input file will contain 9 lines, each containing 9 characters. Each
 * character will be a digit (1-9) or a period (.), representing an empty cell.
 * 
 * The output file should contain the same 9x9 grid, with the empty cells filled
 * in with the correct numbers.
 * 
 * For example, given the following input file:
 * 
 * 53..7.... 6..195... .98....6. 8...6...3 4..8.3..1 7...2...6 .6....28.
 * ...419..5 ....8..79
 * 
 * The output file should be:
 * 
 * 534678912 672195348 198342567 859761423 426853791 713924856 961537284
 * 287419635 345286179
 * 
 * This challenge is broken up into parts. In Programming Club, we will split
 * into teams of 3-4 people to work on each method.
 * 
 * @author Valor Goff + Programming Starter Code
 * 
 *         This implementation implements a private cell class, marks every
 *         non-solved cell 1-9, and then adds every solved cell to a queue. For
 *         every cell in the queue remove the cell's value from every mark list
 *         following soduku's rules. In other words, all rows, columns, and
 *         boxes remove their mark. After a cell has only one mark, it's value
 *         fills in and gets added to the queue.
 */
public class Sudoku {
	// TODO: Revise and add JDOCs
	// TODO: Revise and Separate Cell
	// TODO: Improve Solver
	// TODO: Remove Excess Code
	// TODO: Comment Code Better

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to the Sudoku Solver!");

		Scanner in = new Scanner(System.in);
		System.out.print("Enter the name of the input file (do not include the file extension): ");
		String inputFilename = in.nextLine();
		System.out.print("Enter the name of the output file (do not include the file extension): ");
		String outputFilename = in.nextLine();
		in.close();

		Sudoku app = new Sudoku();
		Cell[][] grid = app.readPuzzle(inputFilename);
		app.solve(grid);
		app.saveSolution(outputFilename, grid);
	}

	/**
	 * Given an input filename, read the Sudoku puzzle from the file and return it
	 * as a 9x9 grid numbers, with no value for spaces in the file.
	 * 
	 * @param filename
	 * @return
	 */
	public Cell[][] readPuzzle(String filename) {
		// TODO: Validate File
		System.out.println("Read sudoku puzzle solution from " + filename + ".sdku");
		Cell[][] grid = new Cell[9][9];
		filename = "spring24/week9/" + filename;

		try (BufferedReader reader = new BufferedReader(new FileReader(filename + ".sdku"))) {
			char[] row;
			int value;

			// build each row
			for (int i = 0; i < 9; ++i) {
				row = reader.readLine().toCharArray();

				// build each column
				for (int j = 0; j < 9; ++j) {
					char c = row[j];
					switch (c) {
					case '.': // blank
						grid[i][j] = new Cell();
						break;
					default: // chars [1-9]
						value = Character.getNumericValue(c);
						grid[i][j] = new Cell(value);
					}
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("File " + filename + " not found in readPuzzle");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Reader IO Exception in readPuzzle");
			e.printStackTrace();
		}

		System.out.println(gridToString(grid));
		return grid;
	}

	private static String gridToString(Cell[][] grid) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 9; ++i) {
			if (i % 3 == 0)
				sb.append("-------------------------\n");
			for (int j = 0; j < 9; ++j) {
				if (j % 3 == 0)
					sb.append("| ");
				sb.append(grid[i][j].getValue());
				sb.append(' ');
			}
			sb.append("|\n");
		}
		sb.append("-------------------------");

		return sb.toString();
	}

	/**
	 * Given an output filename and a 9x9 grid of numbers, write the Sudoku puzzle
	 * to the file.
	 * 
	 * @param grid
	 * @return
	 */
	public void saveSolution(String filename, Cell[][] grid) {
		System.out.println("Write sudoku puzzle solution to " + filename + ".sdku");
		filename = "spring24/week9/" + filename;

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".sdku"))) {
			StringBuilder sb = new StringBuilder();

			// read and write every cell to file
			for (Cell[] row : grid) {
				for (Cell col : row)
					if (col.value == 0)
						sb.append('.'); // blank
					else
						sb.append(col.value);
				sb.append('\n');
			}
			sb.deleteCharAt(sb.length() - 1);// delete last line break (lazy method)

			writer.write(sb.toString());

		} catch (IOException e) {
			System.out.println("Writer IOException in saveSolution");
			e.printStackTrace();
		}
	}

	/**
	 * Determine what numbers are available to be placed in the given cell of the
	 * grid.
	 * 
	 * @param grid
	 * @param row
	 * @param col
	 * @return an array of numbers that are available to be placed in the given cell
	 */
	private void getAvailableNumbers(Cell[][] grid, int row, int col) {
		// TODO: Account for possible invalid inputs and handle an error.
		// TODO: Given the rules of Sudoku, determine what numbers are available to be
		// placed in the given cell of the grid.
		// HINT: Use the getRowRemainingNumbers, getColRemainingNumbers, and
		// getBoxRemainingNumbers methods and save to the Cell.
		System.out.println("TODO: Get available numbers for cell at row " + row + " and column " + col);
	}

	/**
	 * Solve the Sudoku puzzle.
	 * 
	 * @param grid
	 */
	public void solve(Cell[][] grid) {
		// HINT: Use the isValidSolution method to check if the puzzle is solved.
		// HINT: Assume the puzzle is solvable and has only one solution.
		System.out.println("Solve the Sudoku puzzle");

		// add all current values to queue
		Queue<Integer> unmarkList = new ConcurrentLinkedQueue<>();
		for (int i = 0; i < 81; ++i) {
			int x = i % 9; // in range [0-8]
			int y = i / 9; // in range [0-8]
			if (grid[y][x].getValue() != 0) // y,x reads same as when file was created
				unmarkList.add(i);
		}

		// unmark every value from it's row, col, and box
		while (!unmarkList.isEmpty()) {
			System.out.println(unmarkList);
			int cell = unmarkList.remove();

			int x = cell % 9; // in range [0-8]
			int y = cell / 9; // in range [0-8]
			int value = grid[y][x].getValue();
			System.out.println("mapping cell (" + x + "," + y + ") value " + value);

			unmarkRow(cell, unmarkList, grid);
			unmarkCol(cell, unmarkList, grid);
			unmarkBox(cell, unmarkList, grid);
			System.out.println(gridToString(grid));
		}

//		System.out.println(gridToString(grid));
		isValidSolution(grid);
	}

	private static void unmarkRow(int cellID, Queue<Integer> unmarkList, Cell[][] grid) {
		// save cell's value
		int x = cellID % 9; // in range [0-8]
		int y = cellID / 9; // in range [0-8]
		int value = grid[y][x].getValue();
		System.out.println("Unmarking " + value + " from row " + y);

		// unmark cell's value from it's row
		Cell c;
		for (int i = 0; i < 9; ++i) {
			c = grid[y][i]; // y,x reads same as when file was created
			System.out.println("(" + i + "," + y + ") v=" + c.value);
			if (c.getValue() == 0) { // if blank cell
				c.unmark(value);

				// check if that was last unmark and cell got filled
				if (c.getValue() != 0) {
					int newID = i + 9 * y;
					unmarkList.add(newID);
					System.out.println("Adding " + newID);
				}
			}
		}
	}

	private static void unmarkCol(int cellID, Queue<Integer> unmarkList, Cell[][] grid) {
		// save cell's value
		int x = cellID % 9; // in range [0-8]
		int y = cellID / 9; // in range [0-8]
		int value = grid[y][x].getValue();
		System.out.println("Unmarking " + value + " from column " + x);

		// unmark cell's value from it's column
		Cell c;
		for (int i = 0; i < 9; ++i) {
			c = grid[i][x]; // y,x reads same as when file was created
			System.out.println("(" + x + "," + i + ") v=" + c.value);
			if (c.getValue() == 0) { // if blank cell
				c.unmark(value);

				// check if that was last unmark and cell got filled
				if (c.getValue() != 0) {
					int newID = x + 9 * i;
					unmarkList.add(newID);
					System.out.println("Adding " + newID);
				}
			}
		}
	}

	private static void unmarkBox(int cellID, Queue<Integer> unmarkList, Cell[][] grid) {
		// save cell's value
		int x = cellID % 9; // in range [0-8]
		int y = cellID / 9; // in range [0-8]
		int value = grid[y][x].getValue();

		// save cell's box's coordinates
		int box_x = x / 3; // in range [0-2]
		int box_y = y / 3;// in range [0-2]
		System.out.println("Unmarking " + value + " from box (" + box_x + "," + box_y + ")");

		// unmark cell's value from it's box
		// able to re-purpose x and y
		Cell c;
		for (int i = 0; i < 3; ++i) {
			y = 3 * box_y + i;
			for (int j = 0; j < 3; ++j) {
				x = 3 * box_x + j;
				c = grid[y][x];
				System.out.println("(" + x + "," + y + ") v=" + c.value);

				if (c.getValue() == 0) { // if blank cell
					c.unmark(value);

					// check if that was last unmark and cell got filled
					if (c.getValue() != 0) {
						int newID = x + 9 * y;
						unmarkList.add(newID);
						System.out.println("Adding " + newID);
					}
				}
			}
		}
	}

	/**
	 * Get any number between 1 and 9 that is not in the row.
	 * 
	 * @param grid
	 * @param row
	 * @return
	 */
	private ArrayList<Integer> getRowRemainingNumbers(Cell[][] grid, int row) {
		ArrayList<Integer> remainingNumbers = new ArrayList<Integer>();
		for (int i = 1; i <= 9; i++) {
			boolean found = false;
			for (int j = 0; j < 9; j++) {
				if (grid[row][j].getValue() == i) {
					found = true;
					break;
				}
			}
			if (!found) {
				remainingNumbers.add(i);
			}
		}

		return remainingNumbers;
	}

	/**
	 * Get any number between 1 and 9 that is not in the column.
	 * 
	 * @param grid
	 * @param col
	 * @return
	 */
	private ArrayList<Integer> getColRemainingNumbers(Cell[][] grid, int col) {
		ArrayList<Integer> remainingNumbers = new ArrayList<Integer>();
		for (int i = 1; i <= 9; i++) {
			boolean found = false;
			for (int j = 0; j < 9; j++) {
				if (grid[j][col].getValue() == i) {
					found = true;
					break;
				}
			}
			if (!found) {
				remainingNumbers.add(i);
			}
		}

		return remainingNumbers;
	}

	/**
	 * Get any number between 1 and 9 that is not in the box.
	 * 
	 * A box is a 3x3 subgrid of the 9x9 grid.
	 * 
	 * @param grid
	 * @param box
	 * @return
	 */
	private ArrayList<Integer> getBoxRemainingNumbers(Cell[][] grid, int row, int col) {
		ArrayList<Integer> remainingNumbers = new ArrayList<Integer>();
		int boxRow = row % 3;
		int boxCol = col % 3;
		for (int i = 1; i <= 9; i++) {
			boolean found = false;
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					if (grid[boxRow * 3 + j][boxCol * 3 + k].getValue() == i) {
						found = true;
						break;
					}
				}
			}
			if (!found) {
				remainingNumbers.add(i);
			}
		}

		return remainingNumbers;
	}

	/**
	 * Given a list of numbers, return an array of the numbers.
	 * 
	 * A helper method to keep the code using arrays instead of lists whenever
	 * possible.
	 * 
	 * @param list
	 * @return
	 */
	private int[] arrayListToArray(ArrayList<Integer> list) {
		int[] array = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}
		return array;
	}

	/**
	 * Given a 9x9 grid of numbers, return true if the grid is a valid Sudoku puzzle
	 * solution, and false otherwise.
	 * 
	 * A valid Sudoku puzzle is one where each row, column, and 3x3 subgrid contains
	 * the numbers 1-9 exactly once.
	 * 
	 * @param grid a 9x9 grid of numbers
	 * @return true if the grid is a valid Sudoku puzzle, and false otherwise
	 */
	private boolean isValidSolution(Cell[][] grid) {
		// Check that every cell has a value between 1 and 9.
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (grid[i][j].getValue() < 1 || grid[i][j].getValue() > 9) {
					System.out.println("Cell at row " + i + " and column " + j + " has an invalid value.");
					return false;
				}
			}
		}

		// Check that every row contains the numbers 1-9 exactly once.
		for (int i = 0; i < 9; i++) {
			int[] row = new int[9];
			for (int j = 0; j < 9; j++) {
				row[j] = grid[i][j].getValue();
			}
			if (!isValidSet(row)) {
				System.out.println("Row " + i + " is invalid.");
				return false;
			}
		}

		// Check that every column contains the numbers 1-9 exactly once.
		for (int i = 0; i < 9; i++) {
			int[] col = new int[9];
			for (int j = 0; j < 9; j++) {
				col[j] = grid[j][i].getValue();
			}
			if (!isValidSet(col)) {
				System.out.println("Column " + i + " is invalid.");
				return false;
			}
		}

		// Check that every 3x3 subgrid contains the numbers 1-9 exactly once.
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int[] box = new int[9];
				for (int k = 0; k < 3; k++) {
					for (int l = 0; l < 3; l++) {
						box[k * 3 + l] = grid[i * 3 + k][j * 3 + l].getValue();
					}
				}
				if (!isValidSet(box)) {
					System.out.println("Box at row " + i + " and column " + j + " is invalid.");
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Given an array of 9 numbers, return true if the array contains the numbers
	 * 1-9 exactly once, and false otherwise.
	 * 
	 * @param set an array of 9 numbers
	 * @return true if the array contains the numbers 1-9 exactly once, and false
	 *         otherwise
	 */
	private boolean isValidSet(int[] set) {
		boolean[] found = new boolean[9];
		for (int i = 0; i < 9; i++) {
			if (set[i] < 1 || set[i] > 9) {
				return false;
			}
			else if (found[set[i] - 1]) {
				return false;
			}
			else {
				found[set[i] - 1] = true;
			}
		}
		return true;
	}

	private class Cell {
		private static final boolean[] ALL_POSSIBLE = { true, true, true, true, true, true, true, true, true };
		private int value;
		private boolean[] markList;
		private int marks;

		public Cell(int value) {
			setValue(value);
		}

		public Cell() {
			this.value = 0;
			markList = ALL_POSSIBLE.clone();
			marks = markList.length;
		}

		public void setValue(int value) {
			this.value = value;
			markList = null; // to save memory
			marks = 0; // to be clear we have no marks
		}

//		public void mark(int displayValue) {
//			int offsetValue = displayValue - 1;
//
//			if (markList[offsetValue] = false) {
//				markList[offsetValue] = true;
//				marks++;
//			}
//		}

		public void unmark(int displayValue) {
			int offsetValue = displayValue - 1;
			if (markList[offsetValue] == true) {
				markList[offsetValue] = false;
				marks--;
				System.out.println(marks);
				System.out.println(Arrays.toString(markList));
				checkSelf();
				System.out.println("Unmarked.");
			}
			System.out.println("Checked.");
		}

		private void checkSelf() {
			if (marks == 1) {
				System.out.println("Checking self");
				// search markList for remaining mark
				for (int i = 0; i < 9; ++i)
					if (markList[i]) {
						setValue(i + 1); // undo offset for displayValue
						break;
					}
			}
		}

		public int getValue() {
			return value;
		}
	}
}