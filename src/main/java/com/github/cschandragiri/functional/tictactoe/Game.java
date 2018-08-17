package com.github.cschandragiri.functional.tictactoe;

import java.util.Arrays;

import static com.github.cschandragiri.functional.tictactoe.Player.NOBODY;
import static com.github.cschandragiri.functional.tictactoe.Player.O;
import static com.github.cschandragiri.functional.tictactoe.Player.X;
import static com.github.cschandragiri.functional.tictactoe.Status.DRAW;
import static com.github.cschandragiri.functional.tictactoe.Status.GAME_ON;
import static com.github.cschandragiri.functional.tictactoe.Status.SQUARE_ALREADY_PLAYED;

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
        this.status = board.isFull() ? DRAW : status;
        this.board = board;
        this.currentPlayer = currentPlayer;
    }

    public GameState state() {
        if (this.status == DRAW) {
            return new GameState(DRAW, NOBODY);
        }
        return new GameState(this.status, nextPlayer());
    }

    private Player nextPlayer() {
        return currentPlayer == null ? X : currentPlayer == X ? O : X;
    }

    public Game play(Square positionToPlay) {

        if (board.alreadyPlayed(positionToPlay)) {
            return  new Game(SQUARE_ALREADY_PLAYED, board, currentPlayer);
        } else {
            return new Game(GAME_ON, board.take(positionToPlay), nextPlayer());
        }
    }

    public static Game play(Square ... squares) {
        return Arrays.stream(squares).reduce(new Game(), Game::play, (a, b) -> null);
    }
}
