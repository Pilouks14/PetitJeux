package com.nobody.filrouge.games;

import com.nobody.filrouge.players.GamePlayer;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Objects;

public class Puissance4 extends AbstractGame {
	GamePlayer[][] grid = new GamePlayer[6][7];
	int lastX, lastY;

	public Puissance4(GamePlayer ply1, GamePlayer ply2) {
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
		return ply == this.ply1 ? 'R' : (ply == this.ply2 ? 'B' : ' ');
	}

	private Boolean isColumnFull(int x) {
		return this.grid[0][x] != null;
	}

	private GamePlayer checkVector(int x, int y, int i, int j) {
		GamePlayer start = this.grid[x][y];
		return (start == this.grid[x + i][y + j] && start == this.grid[x + 2 * i][y + 2 * j]
				&& start == this.grid[x + 3 * i][y + 3 * j]) ? start : null;
	}

	@Override
	public GamePlayer getWinner() {
		GamePlayer winner = null;

		if (this.lastY > 2) {
			winner = this.checkVector(this.lastY, this.lastX, -1, 0);
			if (winner != null)
				return winner;
		}
		if (this.lastY < 3) {
			winner = this.checkVector(this.lastY, this.lastX, 1, 0);
			if (winner != null)
				return winner;
		}

		if (this.lastX > 2) {
			winner = this.checkVector(this.lastY, this.lastX, 0, -1);
			if (winner != null)
				return winner;

			if (this.lastY > 2) {
				winner = this.checkVector(this.lastY, this.lastX, -1, -1);
				if (winner != null)
					return winner;
			}
			if (this.lastY < 3) {
				winner = this.checkVector(this.lastY, this.lastX, 1, -1);
				if (winner != null)
					return winner;
			}
		}

		if (this.lastX < 4) {
			winner = this.checkVector(this.lastY, this.lastX, 0, 1);
			if (winner != null)
				return winner;

			if (this.lastY > 2) {
				winner = this.checkVector(this.lastY, this.lastX, -1, 1);
				if (winner != null)
					return winner;
			}
			if (this.lastY < 3) {
				winner = this.checkVector(this.lastY, this.lastX, 1, 1);
				if (winner != null)
					return winner;
			}
		}

		return null;
	}

	@Override
	public void play(int moveId) {
		moveId -= 1;
		int y = 0;
		for (int i = 0; i < this.grid.length; i++) {
			if (this.grid[i][moveId] == null)
				y = i;
		}
		this.lastX = moveId;
		this.lastY = y;
		this.grid[y][moveId] = this.getCurrentPlayer();
	}

	@Override
	public boolean isOver() {
		GamePlayer winner = this.getWinner();
		if (winner != null) {
			return true;
		}

		int nbColumns = this.grid[0].length;
		for (int i = 0; i < nbColumns; i++) {
			if (this.isColumnFull(i) == false) {
				return false;
			}
		}

		return true;
	}

	@Override
	public List<Integer> validMoves() {
		List<Integer> moves = new ArrayList<>();

		int nbColumns = this.grid[0].length;
		for (int i = 0; i < nbColumns; i++) {
			if (this.isColumnFull(i) == false) {
				moves.add(i + 1);
			}
		}

		return moves;
	}

	@Override
	public String moveToString(int moveId) {
		return String.format("Column #%d", moveId);
	}

	@Override
	public String situationToString() {
		String grid = "  1   2   3   4   5   6   7  \n";
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[i].length; j++) {
				grid += "| " + this.symbolAt(i, j) + " "; // + this.symbolAt(i, j);
			}
			grid += "|\n|   |   |   |   |   |   |   |\n";
		}
		grid += "+---+---+---+---+---+---+---+\n";
		grid += "  1   2   3   4   5   6   7  \n";
		return grid;
	}

	@Override
	public AbstractGame getCopy() {
		Puissance4 copyTicTacToe = new Puissance4(this.ply1, this.ply2);
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
		if (obj == null || !(obj instanceof Puissance4)) {
			return false;
		} else {
			Puissance4 otherAsTicTacToe = (Puissance4) obj;

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