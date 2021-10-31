package bowling;

import bowling.exception.BowlingException;
import bowling.exception.FrameOverException;
import bowling.exception.InvalidPinQuantityException;

public class Frame {
    public static final int MAX_PINS = 10;
    private final boolean isFinalFrame;
    private int pinsStanding = MAX_PINS;
    private int rollNumber = 0;
    private boolean isOver = false;
    private int rollOnePins = 0;
    private int rollTwoPins = 0;
    private FrameType frameType = FrameType.NORMAL;
    private int score = 0;

    public Frame(boolean isFinalFrame) {
        this.isFinalFrame = isFinalFrame;
    }

    public Frame() {
        this(false);
    }

    public void roll(int numberOfPinsKnockedDown) throws BowlingException {
        if (isOver) {
            throw new FrameOverException("This frame is over, wyd fam");
        }

        if (numberOfPinsKnockedDown > pinsStanding) {
            throw new InvalidPinQuantityException("R u dizzy u knocked over  more pins than there r");
        }

        rollNumber++;
        pinsStanding -= numberOfPinsKnockedDown;

        if (isFinalFrame) {
            rollFinalFrame(numberOfPinsKnockedDown);
        } else {
            rollNonFinalFrame(numberOfPinsKnockedDown);
        }
    }

    public void rollNonFinalFrame(int numberOfPinsKnockedDown) {
        switch (rollNumber) {
            case 1:
                rollOnePins = numberOfPinsKnockedDown;
                score += rollOnePins;

                if (numberOfPinsKnockedDown == MAX_PINS) {
                    this.frameType = FrameType.STRIKE;
                    this.isOver = true;
                }

                break;
            case 2:
                rollTwoPins = numberOfPinsKnockedDown;
                score += rollTwoPins;

                if (rollOnePins + rollTwoPins == MAX_PINS) {
                    this.frameType = FrameType.SPARE;
                }

                this.isOver = true;
                break;
            }
        }

        public void rollFinalFrame(int numberOfPinsKnockedDown) {
            switch (rollNumber) {
                case 1:
                    rollOnePins = numberOfPinsKnockedDown;
                    score += rollOnePins;
                    if (numberOfPinsKnockedDown == MAX_PINS) {
                        this.frameType = FrameType.STRIKE;
                        pinsStanding = MAX_PINS;
                    }
                    break;
                case 2:
                    rollTwoPins = numberOfPinsKnockedDown;
                    score += rollTwoPins;
                    boolean doubleStrike = rollTwoPins == MAX_PINS;

                    if (rollOnePins + rollTwoPins == MAX_PINS) {
                        this.frameType = FrameType.SPARE;
                    }

                    if (FrameType.NORMAL == frameType) {
                        this.isOver = true;
                    } else if (FrameType.SPARE == frameType || doubleStrike) {
                        pinsStanding = MAX_PINS;
                    }
                    break;
                case 3:
                    score += numberOfPinsKnockedDown;
                    this.isOver = true;
                    break;
            }
    }

    public boolean isOver() {
        return this.isOver;
    }

    public int score() {
        return score;
    }

    public FrameType getFrameType() {
        return this.frameType;
    }

    public void addBonus(int bonusPoints) {
        if (!isFinalFrame) {
            this.score += bonusPoints;
        }
    }
}
