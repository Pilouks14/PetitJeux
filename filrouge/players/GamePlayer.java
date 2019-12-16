package com.nobody.filrouge.players;

import com.nobody.filrouge.games.AbstractGame;

/**
 * GamePlayer
 */
public interface GamePlayer {
	int chooseMove(AbstractGame game);

	String getName();
}