package com.github.cschandragiri.functional.tictactoe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest
    @CsvSource({
            "TOP_LEFT, CENTER_LEFT, TOP_MIDDLE, CENTER_MIDDLE, TOP_RIGHT",
            "CENTER_LEFT, TOP_LEFT, CENTER_MIDDLE, TOP_MIDDLE, CENTER_RIGHT",
            "BOTTOM_LEFT, TOP_LEFT, BOTTOM_MIDDLE, TOP_MIDDLE, BOTTOM_RIGHT",
            "TOP_LEFT, CENTER_MIDDLE, CENTER_LEFT, TOP_MIDDLE, BOTTOM_LEFT",
            "TOP_RIGHT, CENTER_MIDDLE, CENTER_RIGHT, TOP_MIDDLE, BOTTOM_RIGHT",
            "TOP_MIDDLE, CENTER_LEFT, CENTER_MIDDLE, TOP_LEFT, BOTTOM_MIDDLE",
            "TOP_LEFT, CENTER_LEFT, CENTER_MIDDLE, TOP_RIGHT, BOTTOM_RIGHT",
            "TOP_RIGHT, CENTER_LEFT, CENTER_MIDDLE, TOP_LEFT, BOTTOM_LEFT"
    })
    void recognize_X_has_won(Square sq1, Square sq2, Square sq3, Square sq4, Square sq5) {
        Game game = Game.play(sq1, sq2, sq3, sq4, sq5);
        assertThat(game.state()).isEqualTo(new GameState(X_HAS_WON, NOBODY));
    }

    @Test
    void recognize_O_has_won() {
        Game game = Game.play(BOTTOM_RIGHT, TOP_LEFT, CENTER_LEFT, TOP_MIDDLE, CENTER_MIDDLE, TOP_RIGHT);
        assertThat(game.state()).isEqualTo(new GameState(O_HAS_WON, NOBODY));
    }

    @Test
    void not_permit_play_after_game_has_won() {
        Game game = Game.play(BOTTOM_RIGHT, TOP_LEFT, CENTER_LEFT, TOP_MIDDLE, CENTER_MIDDLE, TOP_RIGHT,CENTER_RIGHT);
        assertThat(game.state()).isEqualTo(new GameState(O_HAS_WON, NOBODY));
    }

    // X O X
    // O X X
    // O O X
    @Test
    void should_recognize_win_when_won_on_final_square() {
        Game game = Game.play(TOP_LEFT, TOP_MIDDLE, TOP_RIGHT, CENTER_LEFT, CENTER_MIDDLE, BOTTOM_LEFT, CENTER_RIGHT, BOTTOM_MIDDLE, BOTTOM_RIGHT);
        assertThat(game.state()).isEqualTo(new GameState(X_HAS_WON, NOBODY));
    }
}
