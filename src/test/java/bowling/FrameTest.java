package bowling;

import bowling.exception.BowlingException;
import bowling.exception.FrameOverException;
import bowling.exception.InvalidPinQuantityException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FrameTest {
    @Test
    public void nonFinalFrameHasTwoRolls() throws BowlingException {
        Frame frame = new Frame(false);

        frame.roll(1);
        assertThat(frame.isOver(), is(false));

        frame.roll(1);
        assertThat(frame.isOver(), is(true));
    }

    @Test
    public void nonFinalNormalFrameScore() throws BowlingException {
        Frame frame = new Frame();

        frame.roll(1);
        frame.roll(4);

        assertThat(frame.score(), is(1+4));
    }

    @Test
    public void finalNormalFrameHasTwoRolls() throws BowlingException {
        Frame frame = new Frame(true);

        frame.roll(1);
        assertThat(frame.isOver(), is(false));

        frame.roll(1);
        assertThat(frame.isOver(), is(true));
    }

    @Test
    public void nonFinalStrikeFrameHasOneRoll() throws BowlingException {
        Frame frame = new Frame();

        frame.roll(10);
        assertThat(frame.isOver(), is(true));
    }

    @Test
    public void normalFrame() throws BowlingException {
        Frame frame = new Frame();
        frame.roll(1);
        frame.roll(1);
        assertThat(frame.getFrameType(), is(FrameType.NORMAL));
    }

    @Test
    public void spareFrame() throws BowlingException {
        Frame frame = new Frame();
        frame.roll(1);
        frame.roll(9);
        assertThat(frame.getFrameType(), is(FrameType.SPARE));
    }

    @Test
    public void strikeFrame() throws BowlingException {
        Frame frame = new Frame();
        frame.roll(10);
        assertThat(frame.getFrameType(), is(FrameType.STRIKE));
    }

    @Test
    public void nonFinalStrikeFrameEndsAfterOneRoll() throws BowlingException {
        Frame frame = new Frame();
        frame.roll(10);
        assertThat(frame.isOver(), is(true));
    }

    @Test
    public void finalStrikeFrameEndsAfterThreeRolls() throws BowlingException {
        Frame frame = new Frame(true);

        frame.roll(10);
        assertThat(frame.isOver(), is(false));

        frame.roll(1);
        assertThat(frame.isOver(), is(false));

        frame.roll(1);
        assertThat(frame.isOver(), is(true));
    }

    @Test
    public void finalFrameAllStrikesScore() throws BowlingException {
        Frame frame = new Frame(true);

        frame.roll(10);
        frame.roll(10);
        frame.roll(10);

        assertThat(frame.score(), is(30));
    }

    @Test
    public void finalFrameSpareScore() throws BowlingException {
        Frame frame = new Frame(true);

        frame.roll(1);
        frame.roll(9);
        frame.roll(3);

        assertThat(frame.score(), is(1+9+3));
    }

    @Test
    public void finalFrameNormalScore() throws BowlingException {
        Frame frame = new Frame(true);

        frame.roll(2);
        frame.roll(2);

        assertThat(frame.score(), is(4));
    }

    @Test
    public void nonFinalFrameBonusIsApplied() throws BowlingException {
        Frame frame = new Frame();
        frame.roll(10);
        frame.addBonus(3);
        frame.addBonus(4);
        assertThat(frame.score(), is(10+3+4));
    }

    @Test(expected = FrameOverException.class)
    public void frameIsNotRolledWhenOver() throws BowlingException {
        Frame frame = new Frame();
        frame.roll(10);
        frame.roll(1);
    }

    @Test(expected = InvalidPinQuantityException.class)
    public void cantKnockDownMoreThanTenPinsInNormalFrame() throws BowlingException {
        Frame frame = new Frame();
        frame.roll(5);
        frame.roll(6);
    }

    @Test(expected = InvalidPinQuantityException.class)
    public void cantKnockDownMoreThanTenPinsInNormalFrame2() throws BowlingException {
        Frame frame = new Frame();
        frame.roll(11);
    }

    @Test(expected = InvalidPinQuantityException.class)
    public void cantKnockDownMoreThanTenPinsInFinalFrame() throws BowlingException {
        Frame frame = new Frame(true);
        frame.roll(5);
        assertThat(frame.isOver(), is(false));
        frame.roll(6);
    }

    @Test(expected = InvalidPinQuantityException.class)
    public void cantKnockDownMoreThanTenPinsInFinalFrame2() throws BowlingException {
        Frame frame = new Frame(true);
        frame.roll(11);
    }

    @Test(expected = InvalidPinQuantityException.class)
    public void cantKnockDownMoreThanTenPinsAfterStrikeInFinalFrame() throws BowlingException {
        Frame frame = new Frame(true);
        frame.roll(10);
        assertThat(frame.isOver(), is(false));
        frame.roll(11);
    }

    @Test(expected = InvalidPinQuantityException.class)
    public void cantKnockDownMoreThanTenPinsAfterStrikeInFinalFrame2() throws BowlingException {
        Frame frame = new Frame(true);
        frame.roll(10);
        assertThat(frame.isOver(), is(false));
        frame.roll(5);
        assertThat(frame.isOver(), is(false));
        frame.roll(6);
    }

    @Test(expected = InvalidPinQuantityException.class)
    public void cantKnockDownMoreThanTenPinsAfterTwoStrikesInFinalFrame() throws BowlingException {
        Frame frame = new Frame(true);
        frame.roll(10);
        assertThat(frame.isOver(), is(false));
        frame.roll(10);
        assertThat(frame.isOver(), is(false));
        frame.roll(11);
    }

    @Test(expected = InvalidPinQuantityException.class)
    public void cantKnockDownMoreThanTenPinsAfterSpareInFinalFrame() throws BowlingException {
        Frame frame = new Frame(true);
        frame.roll(1);
        assertThat(frame.isOver(), is(false));
        frame.roll(9);
        assertThat(frame.isOver(), is(false));
        frame.roll(11);
    }
}