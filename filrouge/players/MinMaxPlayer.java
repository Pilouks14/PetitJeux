package com.nobody.filrouge.players;

import com.nobody.filrouge.games.AbstractGame;

public class MinMaxPlayer implements GamePlayer {

	public MinMaxPlayer() {
	}

	public int evaluer(AbstractGame game) {
		if (game.isOver()) {
			GamePlayer winner = game.getWinner();

			if (winner == game.getCurrentPlayer()) {
				return 1;
			} else if (winner != null) {
				return -1;
			} else {
				return 0;
			}

		} else {
			int res = Integer.MIN_VALUE;
			for (int move : game.validMoves()) {
				AbstractGame gameState = game.getCopy();
				gameState.playAndSwap(move);
				int result = -this.evaluer(gameState);
				if (result > res) {
					res = result;
				}
			}
			return res;
		}
	}

	public int negamax(AbstractGame game) {
		int meilleurValeur = Integer.MIN_VALUE;
		int meilleurCoup = 0;

		for (int move : game.validMoves()) {
			AbstractGame gameState = game.getCopy();
			gameState.playAndSwap(move);
			int result = -this.evaluer(gameState);
			// System.out.println(game.moveToString(move) + " - " + result);
			if (result > meilleurValeur) {
				meilleurValeur = result;
				meilleurCoup = move;
			}
		}

		return meilleurCoup;
	}

	public int chooseMove(AbstractGame game) {
		int move = this.negamax(game);
		System.out.println(this.getName() + " play \"" + game.moveToString(move) + "\"");

		return move;
	}

	public String getName() {
		return "Joueur MinMax #" + this.hashCode();
	}
}