package com.nobody.filrouge.games;

import com.nobody.filrouge.players.GamePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Nim extends AbstractGame {
	int initialNbMatches;
	int nbMatches;
	int maxMatchesPerPick;

	public Nim(GamePlayer ply1, GamePlayer ply2, int nbMatches, int maxMatchesPerPick) {
		super(ply1, ply2);
		this.initialNbMatches = nbMatches;
		this.nbMatches = nbMatches;
		this.maxMatchesPerPick = maxMatchesPerPick;
	}

	public int getInitialNbMatches() {
		return this.initialNbMatches;
	}

	public int getNbMatches() {
		return this.nbMatches;
	}

	public String situationToString() {
		return "Il reste " + this.getNbMatches() + " allumettes !";
	}

	public int getNbMaxMatchesPerPick() {
		return this.maxMatchesPerPick;
	}

	@Override
	public String moveToString(int move) {
		return String.format("Retirer %d allumettes.", move);
	}

	@Override
	public List<Integer> validMoves() {
		List<Integer> moves = new ArrayList<>();

		int maxMove = this.maxMatchesPerPick > this.nbMatches ? this.nbMatches : this.maxMatchesPerPick;
		for (int i = 1; i <= maxMove; i++) {
			moves.add(i);
		}

		return moves;
	}

	@Override
	public void play(int nbMatches) {
		this.nbMatches -= nbMatches;
	}

	@Override
	public boolean isOver() {
		return this.nbMatches == 0;
	}

	@Override
	public GamePlayer getWinner() {
		if (!this.isOver())
			return null;
		return this.getCurrentPlayer();
	}

	@Override
	public AbstractGame getCopy() {
		Nim copyNim = new Nim(this.ply1, this.ply2, this.nbMatches, this.maxMatchesPerPick);
		copyNim.curPly = this.curPly;
		return copyNim;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Nim)) {
			return false;
		} else {
			Nim otherAsNim = (Nim) obj;

			if (!this.curPly.equals(otherAsNim.curPly))
				return false;

			if (this.nbMatches != otherAsNim.nbMatches)
				return false;
			if (this.maxMatchesPerPick != otherAsNim.maxMatchesPerPick)
				return false;
			if (this.initialNbMatches != otherAsNim.initialNbMatches)
				return false;
			return true;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.curPly, this.nbMatches, this.initialNbMatches, this.maxMatchesPerPick);
	}
}