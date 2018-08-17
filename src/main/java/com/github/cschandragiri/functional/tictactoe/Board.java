package com.github.cschandragiri.functional.tictactoe;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.cschandragiri.functional.tictactoe.Square.*;
import static java.util.Collections.emptyMap;

public class Board {

    private final Map<Square, Player> squaresTaken;

    public Board() {
        this.squaresTaken = emptyMap();
    }

    private Board(Map<Square, Player> squaresTaken) {
        this.squaresTaken = squaresTaken;
    }

    public boolean alreadyPlayed(Square positionToPlay) {
        return this.squaresTaken.keySet()
                .contains(positionToPlay);
    }

    public Board take(Square positionToPlay, Player player) {
        Map<Square, Player> squares = new HashMap<>(squaresTaken);
        squares.put(positionToPlay, player);
        return new Board(squares);
    }

    public boolean isFull() {
        return squaresTaken.entrySet()
                .size() == 9;
    }

    public boolean hasWon(Player currentPlayer) {
        Stream<Stream<Square>> winningCombos = Stream.of(
                Stream.of(TOP_LEFT, TOP_MIDDLE, TOP_RIGHT),
                Stream.of(CENTER_LEFT, CENTER_MIDDLE, CENTER_RIGHT),
                Stream.of(BOTTOM_LEFT, BOTTOM_MIDDLE, BOTTOM_RIGHT),
                Stream.of(TOP_LEFT, CENTER_LEFT, BOTTOM_LEFT),
                Stream.of(TOP_MIDDLE, CENTER_MIDDLE, BOTTOM_MIDDLE),
                Stream.of(TOP_RIGHT, CENTER_RIGHT, BOTTOM_RIGHT),
                Stream.of(TOP_LEFT, CENTER_MIDDLE, BOTTOM_RIGHT),
                Stream.of(TOP_RIGHT, CENTER_MIDDLE, BOTTOM_LEFT)
        );
        Set<Square> squaresTakenByCurrentPlayer = squaresTaken.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == currentPlayer)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        return winningCombos.anyMatch(combo ->
                combo.allMatch(squaresTakenByCurrentPlayer::contains)
        );
    }
}
