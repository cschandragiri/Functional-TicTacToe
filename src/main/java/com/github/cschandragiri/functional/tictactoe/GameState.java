package com.github.cschandragiri.functional.tictactoe;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = {"status", "nextPlayer"})
@ToString(of = {"status", "nextPlayer"})
public class GameState {

    private final Status status;
    private final Player nextPlayer;

    GameState(Status status, Player nextPlayer) {
        this.status = status;
        this.nextPlayer = nextPlayer;
    }
}
