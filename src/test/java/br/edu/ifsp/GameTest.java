package br.edu.ifsp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class GameTest {

    private Game game;

    @BeforeEach
    void setUp(){
        game = new Game();
    }

    @Test
    @DisplayName("Should score zero in gutter game")
    void shouldScoreZeroInGutterGame() {
        rollMany(20, 0);
        assertThat(game.score()).isEqualTo(0);
    }

    @Test
    @DisplayName("Should score 40 if always score 2")
    void shouldScore40IfAlwaysScore2() {
        rollMany(20, 2);
        assertThat(game.score()).isEqualTo(40);
    }

    @Test
    @DisplayName("Should get bonus if scores a spare")
    void shouldGetBonusIfScoresASpare() {
        rollSpare();
        game.roll(7);
        rollMany(17, 0);
        assertThat(game.score()).isEqualTo(24);
    }

    @Test
    @DisplayName("Should get bonus if scores a strike")
    void shouldGetBonusIfScoresAStrike() {
        rollStrike();
        game.roll(3);
        game.roll(3);
        rollMany(16, 0);
        assertThat(game.score()).isEqualTo(22);
    }

    @Test
    @DisplayName("Should score 300 if strikes all rolls")
    void shouldScore300IfStrikesAllRolls() {
        rollMany(12, 10);
        assertThat(game.score()).isEqualTo(300);
    }

    @Test
    @DisplayName("Should score 15 if scores 5 in the three last rolls")
    void shouldScore15IfScores5InTheThreeLastRolls() {
        rollMany(18, 0);
        rollSpare();
        game.roll(5);
        assertThat(game.score()).isEqualTo(15);
    }


    private void rollMany(int numberOfRolls, int pins) {
        IntStream.range(0, numberOfRolls).forEach(unused -> game.roll(pins));
    }

    private void rollSpare() {
        game.roll(5);
        game.roll(5);
    }

    private void rollStrike() {
        game.roll(10);
    }
}
  