package com.github.cschandragiri.functional.tictactoe;

import java.util.Arrays;
import java.util.EnumSet;

import static com.github.cschandragiri.functional.tictactoe.Player.NOBODY;
import static com.github.cschandragiri.functional.tictactoe.Player.O;
import static com.github.cschandragiri.functional.tictactoe.Player.X;
import static com.github.cschandragiri.functional.tictactoe.Status.*;

public class Game {

    private final Player currentPlayer;
    private final Board board;
    private final Status status;

    public Game() {
        this.status = GAME_ON;
        this.board = new Board();
        this.currentPlayer = null;
    }

    private Game(Status status, Board board, Player currentPlayer) {
        this.board = board;
        this.currentPlayer = currentPlayer;
        if (board.hasWon(currentPlayer)) {
            this.status = currentPlayer == X ? X_HAS_WON : O_HAS_WON;
        } else if (board.isFull()) {
            this.status = DRAW;
        } else {
            this.status = status;
        }
    }

    public GameState state() {
        if (EnumSet.of(DRAW, X_HAS_WON, O_HAS_WON).contains(this.status)) {
            return new GameState(status, NOBODY);
        }
        return new GameState(status, nextPlayer());
    }

    private Player nextPlayer() {
        return currentPlayer == null ? X : currentPlayer == X ? O : X;
    }

    public Game play(Square positionToPlay) {
        if (gameIsOver()) {
            return this;
        }
        if (board.alreadyPlayed(positionToPlay)) {
            return  new Game(SQUARE_ALREADY_PLAYED, board, currentPlayer);
        } else {
            return new Game(GAME_ON, board.take(positionToPlay, nextPlayer()), nextPlayer());
        }
    }

    private boolean gameIsOver() {
        return EnumSet.of(DRAW, X_HAS_WON, O_HAS_WON).contains(this.status);
    }

    public static Game play(Square ... squares) {
        return Arrays.stream(squares).reduce(new Game(), Game::play, (a, b) -> null);
    }
}
