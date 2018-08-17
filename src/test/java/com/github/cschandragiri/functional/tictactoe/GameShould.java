package com.github.cschandragiri.functional.tictactoe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.cschandragiri.functional.tictactoe.Player.NOBODY;
import static com.github.cschandragiri.functional.tictactoe.Player.X;
import static com.github.cschandragiri.functional.tictactoe.Square.*;
import static com.github.cschandragiri.functional.tictactoe.Status.*;
import static com.github.cschandragiri.functional.tictactoe.Status.GAME_ON;
import static com.github.cschandragiri.functional.tictactoe.Status.SQUARE_ALREADY_PLAYED;
import static org.fest.assertions.Assertions.assertThat;

@DisplayName("Game should")
public class GameShould {

    @Test
    void wait_for_wait_for_x_to_play_first() {
        Game game = new Game();
        assertThat(game.state()).isEqualTo(new GameState(GAME_ON, X));
    }

    @Test
    void player_should_alternate() {
        Game game = new Game();
        game = game.play(Square.TOP_LEFT);
        game = game.play(Square.TOP_MIDDLE);
        assertThat(game.state()).isEqualTo(new GameState(GAME_ON, X));
    }

    @Test
    void not_allow_same_square_to_be_played_twice() {
        Game game = Game.play(TOP_LEFT, TOP_RIGHT, TOP_LEFT);
        assertThat(game.state()).isEqualTo(new GameState(SQUARE_ALREADY_PLAYED, X));
    }

    // X 0 X
    // 0 X X
    // 0 X 0
    @Test
    void should_recognize_draw() {
        Game game = Game.play(TOP_LEFT, TOP_MIDDLE, TOP_RIGHT, CENTER_LEFT, CENTER_MIDDLE, BOTTOM_LEFT, CENTER_RIGHT, BOTTOM_RIGHT, BOTTOM_MIDDLE);
        assertThat(game.state()).isEqualTo(new GameState(DRAW, NOBODY));
    }
}
