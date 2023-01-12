package br.edu.ifsp;

public class Game {

    int[] rolls = new int[21];
    int rollCount = 0;

    public void roll(int pins) {
        rolls[rollCount++] += pins;
    }

    public int score() {
        int score = 0;
        int rollIndex = 0;
        for (int i = 0; i < 10; i++) {
            if(isStrike(rollIndex)){
                score += 10 + strikeBonus(rollIndex);
                rollIndex++;
            } else if(isSpare(rollIndex)){
                score += 10 + spareBonus(rollIndex);
                rollIndex += 2;
            }else {
                score += frameScore(rollIndex);
                rollIndex += 2;
            }
        }
        return score;
    }

    private boolean isStrike(int rollIndex) {
        return rolls[rollIndex] == 10;
    }

    private int strikeBonus(int rollIndex) {
        return rolls[rollIndex+1] + rolls[rollIndex+2];
    }

    private boolean isSpare(int rollIndex) {
        return frameScore(rollIndex) == 10;
    }

    private int spareBonus(int rollIndex) {
        return rolls[rollIndex + 2];
    }

    private int frameScore(int rollIndex) {
        return rolls[rollIndex] + rolls[rollIndex + 1];
    }
}
