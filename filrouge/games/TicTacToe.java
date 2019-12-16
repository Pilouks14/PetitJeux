package com.nobody.filrouge.games;

import com.nobody.filrouge.players.GamePlayer;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Objects;

public class TicTacToe extends AbstractGame {
	GamePlayer[][] grid = new GamePlayer[3][3];

	public TicTacToe(GamePlayer ply1, GamePlayer ply2) {
		super(ply1, ply2);
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[i].length; j++) {
				// this.grid[i][j] = (char) (49 + i * 3 + j);
				this.grid[i][j] = null;
			}
		}
	}

	private char symbolAt(int x, int y) {
		GamePlayer ply = this.grid[x][y];
		return ply == this.ply1 ? 'X' : (ply == this.ply2 ? 'O' : (char) (49 + x * 3 + y));
	}

	private GamePlayer checkVector(int x, int y, int i, int j) {
		GamePlayer start = this.grid[x][y];
		return (start == this.grid[x + i][y + j] && start == this.grid[x + 2 * i][y + 2 * j]) ? start : null;
	}

	@Override
	public GamePlayer getWinner() {
		GamePlayer winner = null;

		for (int i = 0; i < 3; i++) {
			winner = this.checkVector(0, i, 1, 0);
			if (winner != null)
				return winner;
		}

		// Check Columns
		for (int i = 0; i < 3; i++) {
			winner = this.checkVector(i, 0, 0, 1);
			if (winner != null)
				return winner;
		}

		// Check Diagonals
		winner = this.checkVector(0, 0, 1, 1);
		if (winner != null)
			return winner;

		winner = this.checkVector(2, 2, -1, -1);
		if (winner != null)
			return winner;

		return null;
	}

	@Override
	public void play(int moveId) {
		moveId -= 1;
		int y = moveId / 3;
		int x = moveId % 3;
		this.grid[y][x] = this.getCurrentPlayer();
	}

	@Override
	public boolean isOver() {
		GamePlayer winner = this.getWinner();
		if (winner != null) {
			return true;
		}

		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[i].length; j++) {
				if (this.grid[i][j] == null) {
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public List<Integer> validMoves() {
		List<Integer> moves = new ArrayList<>();

		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[i].length; j++) {
				GamePlayer value = this.grid[i][j];
				if (value == null) {
					moves.add(i * 3 + j + 1);
				}
			}
		}

		return moves;
	}

	@Override
	public String moveToString(int moveId) {
		moveId -= 1;
		int y = moveId / 3;
		int x = moveId % 3;
		return String.format("Case(%d, %d)", x + 1, y + 1);
	}

	@Override
	public String situationToString() {
		String grid = "";
		grid += "   |   |   \n";
		grid += String.format(" %c | %c | %c \n", this.symbolAt(0, 0), this.symbolAt(0, 1), this.symbolAt(0, 2));
		grid += "   |   |   \n";
		grid += "---+---+---\n";
		grid += "   |   |   \n";
		grid += String.format(" %c | %c | %c \n", this.symbolAt(1, 0), this.symbolAt(1, 1), this.symbolAt(1, 2));
		grid += "   |   |   \n";
		grid += "---+---+---\n";
		grid += "   |   |   \n";
		grid += String.format(" %c | %c | %c \n", this.symbolAt(2, 0), this.symbolAt(2, 1), this.symbolAt(2, 2));
		grid += "   |   |   \n";
		return grid;
	}

	@Override
	public AbstractGame getCopy() {
		TicTacToe copyTicTacToe = new TicTacToe(this.ply1, this.ply2);
		copyTicTacToe.curPly = this.curPly;

		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[i].length; j++) {
				copyTicTacToe.grid[i][j] = this.grid[i][j];
			}
		}

		return copyTicTacToe;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof TicTacToe)) {
			return false;
		} else {
			TicTacToe otherAsTicTacToe = (TicTacToe) obj;

			if (!this.curPly.equals(otherAsTicTacToe.curPly))
				return false;

			for (int i = 0; i < this.grid.length; i++) {
				for (int j = 0; j < this.grid[i].length; j++) {
					if (otherAsTicTacToe.grid[i][j] != this.grid[i][j]) {
						return false;
					}
				}
			}

			return true;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.curPly, Arrays.hashCode(this.grid));
	}
}