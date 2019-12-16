package com.nobody.filrouge;

import java.util.Scanner;

import com.nobody.filrouge.games.*;
import com.nobody.filrouge.orchestration.Orchestrator;
import com.nobody.filrouge.players.*;

public class Main {

	public static void main(String[] args) {
		int argIndex = 0;
		GamePlayer ply1 = null;
		GamePlayer ply2 = null;

		if (args.length > argIndex) {
			String plyType = args[argIndex++];
			switch (plyType) {
			case "human":
				if (args.length > argIndex) {
					ply1 = new Human(args[argIndex++]);
				} else {
					throw new IllegalArgumentException("Provide a name for the player.");
				}
				break;
			case "random":
				ply1 = new RandomPlayer();
				break;
			case "minmax":
				ply1 = new MinMaxPlayer();
				break;
			case "bminmax":
				ply1 = new BufferedMinMaxPlayer();
				break;
			default:
				throw new IllegalArgumentException("Provide a valid player type.");
			}
		} else {
			throw new IllegalArgumentException("Provide a valid player type.");
		}

		if (args.length > argIndex) {
			String plyType = args[argIndex++];
			switch (plyType) {
			case "human":
				if (args.length > argIndex) {
					ply2 = new Human(args[argIndex++]);
				} else {
					throw new IllegalArgumentException("Provide a name for the player.");
				}
				break;
			case "random":
				ply2 = new RandomPlayer();
				break;
			case "minmax":
				ply2 = new MinMaxPlayer();
				break;
			case "bminmax":
				ply2 = new BufferedMinMaxPlayer();
				break;
			default:
				throw new IllegalArgumentException("Provide a valid player type.");
			}
		} else {
			throw new IllegalArgumentException("Provide a valid player type.");
		}

		AbstractGame game;
		switch (args[argIndex++]) {
		case "nim":
			int nbMatches = Integer.parseInt(args[argIndex++]);
			int maxNb = Integer.parseInt(args[argIndex++]);
			game = new Nim(ply1, ply2, nbMatches, maxNb);
			break;
		case "morpion":
			game = new TicTacToe(ply1, ply2);
			break;
		case "puissance4":
			game = new Puissance4(ply1, ply2);
			break;

		default:
			return;
		}
		Orchestrator.playGame(game);
		System.exit(0);
	}
}