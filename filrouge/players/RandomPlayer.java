package com.nobody.filrouge.players;

import java.util.List;
import java.util.Random;

import com.nobody.filrouge.games.AbstractGame;

public class RandomPlayer implements GamePlayer {
	Random randomGenerator;

	public RandomPlayer() {
		this.randomGenerator = new Random();
	}

	public int chooseMove(AbstractGame game) {
		List<Integer> coups = game.validMoves();
		int rnd = this.randomGenerator.nextInt(coups.size());
		int move = coups.get(rnd);
		System.out.println(this.getName() + " play \"" + game.moveToString(move) + "\"");
		return move;
	}

	public String getName() {
		return "Joueur aléatoire #" + this.hashCode();
	}
}