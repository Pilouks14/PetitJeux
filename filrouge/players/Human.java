package com.nobody.filrouge.players;

import java.util.List;
import java.util.Scanner;

import com.nobody.filrouge.games.AbstractGame;

public class Human implements GamePlayer {
	String name;

	public Human(String name) {
		this.name = name;
	}

	public int chooseMove(AbstractGame game) {
		int choice;
		Scanner scanner = new Scanner(System.in);
		List<Integer> validMoves = game.validMoves();
		do {
			for (int move : validMoves) {
				String str = String.format("%d - %s", move, game.moveToString(move));
				System.out.println(str);
			}
			choice = scanner.nextInt();
			scanner.nextLine();
		} while (!validMoves.contains(choice));
		return choice;
	}

	public String getName() {
		return this.name;
	}
}