package com.github.cschandragiri.functional.tictactoe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.cschandragiri.functional.tictactoe.Player.*;
import static com.github.cschandragiri.functional.tictactoe.Status.*;
import static org.fest.assertions.Assertions.assertThat;

@DisplayName("Game should")
public class GameShould {

    @Test
    public void wait_for_wait_for_x_to_play_first() {
        Game game = new Game();
        assertThat(game.state()).isEqualTo(new GameState(GAME_ON, X));
    }

    @Test
    public void wait_for_O_to_play_after_x() {
        Game game = new Game();
        game = game.play();
        assertThat(game.state()).isEqualTo(new GameState(GAME_ON, O));
    }

    @Test
    public void player_should_alternate() {
        Game game = new Game();
        game = game.play();
        game = game.play();
        assertThat(game.state()).isEqualTo(new GameState(GAME_ON, X));
    }
}
