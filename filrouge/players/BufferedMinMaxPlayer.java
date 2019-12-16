package com.nobody.filrouge.players;

import java.util.HashMap;
import java.util.Map;

import com.nobody.filrouge.games.AbstractGame;

public class BufferedMinMaxPlayer extends MinMaxPlayer implements GamePlayer {
	Map<AbstractGame, Integer> buffer;

	public BufferedMinMaxPlayer() {
		super();
		this.buffer = new HashMap<AbstractGame, Integer>();
	}

	public int chooseMove(AbstractGame game) {
		int move;
		if (this.buffer.containsKey(game)) {
			move = this.buffer.get(game);
			if (!game.validMoves().contains(move)) {
				move = super.chooseMove(game);
				this.buffer.put(game, move);
			}
		} else {
			move = super.chooseMove(game);
			this.buffer.put(game, move);
		}

		return move;
	}

	public String getName() {
		return "Joueur BufferedMinMax #" + this.hashCode();
	}
}