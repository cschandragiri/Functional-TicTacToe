package com.github.cschandragiri.functional.tictactoe;

import static com.github.cschandragiri.functional.tictactoe.Player.O;
import static com.github.cschandragiri.functional.tictactoe.Player.X;
import static com.github.cschandragiri.functional.tictactoe.Status.GAME_ON;

public class Game {

    private final Player currentPlayer;

    public Game() {
        this.currentPlayer = null;
    }

    private Game(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public GameState state() {
        return new GameState(GAME_ON, nextPlayer());
    }

    private Player nextPlayer() {
        return currentPlayer == null ? X : currentPlayer == X ? O : X;
    }

    public Game play() {
        return new Game(nextPlayer());
    }
}
