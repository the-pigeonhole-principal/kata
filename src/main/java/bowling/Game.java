package bowling;

import bowling.exception.BowlingException;
import bowling.exception.GameOverException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Game {
    private static int NUMBER_OF_FRAMES = 10;
    private static int LAST_FRAME_INDEX = NUMBER_OF_FRAMES - 1;
    private Frame[] gameFrames = new Frame[NUMBER_OF_FRAMES];
    private int currentFrameIndex = 0;
    private Set<Frame> applyNextRollBonus = new HashSet<>();
    private Frame applyNextNextRollBonus;
    private boolean gameOver = false;

    public Game() {
        for (int frameNumber = 0; frameNumber < LAST_FRAME_INDEX; frameNumber++) {
            gameFrames[frameNumber] = new Frame();
        }
        gameFrames[LAST_FRAME_INDEX] = new Frame(true);
    }

    public void roll(int numberOfPinsKnockedDown) throws BowlingException {
        if (gameOver) {
            throw new GameOverException("game over bb sorry x");
        }

        Frame currentFrame = gameFrames[currentFrameIndex];
        currentFrame.roll(numberOfPinsKnockedDown);

        for (Frame frameWhichRequiresBonusPoints : applyNextRollBonus) {
            frameWhichRequiresBonusPoints.addBonus(numberOfPinsKnockedDown);
        }
        applyNextRollBonus.clear();
        if (applyNextNextRollBonus != null)
        {
            applyNextRollBonus.add(applyNextNextRollBonus);
        }
        applyNextNextRollBonus = null;

        if (currentFrame.getFrameType() == FrameType.SPARE) {
            applyNextRollBonus.add(currentFrame);
        } else if (currentFrame.getFrameType() == FrameType.STRIKE) {
            applyNextRollBonus.add(currentFrame);
            applyNextNextRollBonus = currentFrame;
        }

        if (currentFrame.isOver()) {
            currentFrameIndex++;
            if (currentFrameIndex > LAST_FRAME_INDEX) {
                gameOver = true;
            }
        }
    }

    public int score() {
        return Arrays.stream(gameFrames).mapToInt(Frame::score).sum();
    }

    public boolean gameOver() {
        return gameOver;
    }
}
