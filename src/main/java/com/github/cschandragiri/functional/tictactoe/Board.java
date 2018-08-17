package com.github.cschandragiri.functional.tictactoe;


import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.emptySet;

public class Board {

    private final Set<Square> squaresTaken;

    public Board() {
        this.squaresTaken = emptySet();
    }

    private Board(Set<Square> squaresTaken) {
        this.squaresTaken = squaresTaken;
    }

    public boolean alreadyPlayed(Square positionToPlay) {
        return this.squaresTaken.contains(positionToPlay);
    }

    public Board take(Square positionToPlay) {
        Set<Square> squares = new HashSet<Square>(squaresTaken);
        squares.add(positionToPlay);
        return new Board(squares);
    }

    public boolean isFull() {
        return squaresTaken.size() == 9;
    }
}
