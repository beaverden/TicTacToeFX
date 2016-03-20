package com.TicTacToe;

import javafx.scene.text.Text;

public class Game {
	protected Text[][] letters; // Letters X and O on the grid
	protected Board currentBoard; // Game board [3][3]
	protected int playerTurn; // Current turn. Default = 1
	protected int result; // Game result. Default = 0
	
	
	/**
	 * Takes care of all the UI and result changes after the move
	 * @param i
	 * @param j
	 */
	protected void move(int i, int j) {
		currentBoard.activate(i, j, playerTurn); 

		nextTurn();

		getResult(); //Updates the result after the move
 
		redrawLetters(); //Updates the letters after the move

	}
	
	protected void nextTurn() {
		playerTurn = (playerTurn == 1) ? 2 : 1;
	}

	protected int getTurn() {
		return playerTurn;
	}
	/**
	 * Turns the move into a letter. 1 = X, 2 = O
	 * @return String - letter
	 */
	protected String getStringTurn() {
		if (playerTurn == 1)
			return "X";
		else
			return "O";
	}

	protected String getStringTurn(int val) {
		if (val == 1)
			return "X";
		else
			return "O";
	}

	protected int getIntResult() {
		result = currentBoard.checkResult();
		return result;
	}
	
	/**
	 * Gets the result and transforms it into a human readable string 
	 * From adding into the label
	 * @return String - results
	 */
	public String getResult() {
		result = currentBoard.checkResult();
		if (result == 0) {
			if (playerTurn == 1)
				return "AI_1 moves";
			else
				return "AI_2 moves";
		} else if (result > 0 && result < 3) {
			if (result == 1)
				return "AI_1 wins!";
			else
				return "AI_2 wins!";
		} else {
			return "Draw!";
		}
	}
	
	/**
	 * Checks whether a given cell is filled with X or O
	 * @param i
	 * @param j
	 * @return boolean 
	 */
	public boolean isActivated(int i, int j) {
		return (currentBoard.getValue(i, j) > 0);
	}

	/**
	 * Checks whether the match is finished
	 * @return
	 */
	public boolean isFinished() {
		return (result > 0);
	}

	public void redrawLetters() {
		if (letters == null) return;
		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 3; j++) {
				int col = currentBoard.getColor(i, j);
				int val = currentBoard.getValue(i, j);

				letters[i][j].setText(getStringTurn(val));

				if (col == 1) {

					letters[i][j].getStyleClass().clear();
					letters[i][j].getStyleClass().addAll("letter", "letteractive");
					letters[i][j].setOpacity(1);

				} else if (col == 2) {

					letters[i][j].getStyleClass().clear();
					letters[i][j].getStyleClass().addAll("letter", "letterwinning");
					letters[i][j].setOpacity(1);

				} else {

					letters[i][j].getStyleClass().clear();
					letters[i][j].getStyleClass().addAll("letter", "letterinactive");
					letters[i][j].setOpacity(0);

				}
			}
		}
	}
}
