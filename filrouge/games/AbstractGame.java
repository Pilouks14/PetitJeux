package com.nobody.filrouge.games;

import java.util.List;
import com.nobody.filrouge.players.GamePlayer;

/**
 * AbstractGame
 */
abstract public class AbstractGame {
	GamePlayer ply1;
	GamePlayer ply2;
	GamePlayer curPly;

	public AbstractGame(GamePlayer ply1, GamePlayer ply2) {
		this.ply1 = ply1;
		this.ply2 = ply2;
		this.curPly = ply1;
	}

	abstract public void play(int move);

	public void playAndSwap(int move) {
		this.play(move);
		this.curPly = this.curPly == this.ply1 ? this.ply2 : this.ply1;
	}

	public GamePlayer getCurrentPlayer() {
		return this.curPly;
	}

	abstract public AbstractGame getCopy();

	abstract public List<Integer> validMoves();

	abstract public String situationToString();

	abstract public String moveToString(int move);

	abstract public boolean isOver();

	abstract public GamePlayer getWinner();
}