package com.nobody.filrouge.orchestration;

import com.nobody.filrouge.games.AbstractGame;
import com.nobody.filrouge.players.GamePlayer;

import java.util.Scanner;

public class Orchestrator {

	public static void playGame(AbstractGame game) {
		Scanner scanner = new Scanner(System.in);
		while (!game.isOver()) {
			GamePlayer ply = game.getCurrentPlayer();
			System.out.println("\n");
			System.out.println(game.situationToString());
			System.out.println(ply.getName() + " is now playing !");

			int move = ply.chooseMove(game);
			game.playAndSwap(move);
		}
		scanner.close();

		System.out.println(game.situationToString());

		GamePlayer winner = game.getWinner();
		if (winner != null) {
			System.out.println(winner.getName() + " WON THE GAME ! ! !");
		} else {
			System.out.println("IT'S A DRAW ! ! !");
		}
	}
}