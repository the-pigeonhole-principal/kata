package bowling;

import bowling.exception.BowlingException;
import bowling.exception.GameOverException;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GameTest {
    @Test
    public void normalFrameScore() throws BowlingException {
        Game game = new Game();
        game.roll(1);
        game.roll(2);

        assertThat(game.score(), is(1+2));
    }

    @Test
    public void twoNormalFramesScore() throws BowlingException {
        Game game = new Game();
        game.roll(1);
        game.roll(2);
        game.roll(3);
        game.roll(4);

        assertThat(game.score(), is(1+2+3+4));
    }

    @Test
    public void spareHasBonusPointsAdded() throws BowlingException {
        Game game = new Game();

        game.roll(1);
        game.roll(9);

        game.roll(3);
        game.roll(4);

        assertThat(game.score(), is(1+9 + 3+3 + 4));
    }

    @Test
    public void strikeHasBonusPointsAdded() throws BowlingException {
        Game game = new Game();

        game.roll(10);

        game.roll(3);
        game.roll(4);

        assertThat(game.score(), is(10 + (3+4) + (3+4)));
    }

    @Test(expected = GameOverException.class)
    public void gameEndsAfter10Rounds() throws BowlingException {
        Game game = new Game();

        for (int round = 1; round <= 10; round++) {
            assertThat(game.gameOver(), is(false));
            game.roll(1);
            game.roll(1);
        }

        assertThat(game.gameOver(), is(true));

        game.roll(1);
    }

    @Test
    public void allStrikes() throws BowlingException {
        Game game = new Game();

        game.roll(10);
        Assert.assertFalse(game.gameOver());

        game.roll(10);
        Assert.assertFalse(game.gameOver());

        game.roll(10);
        Assert.assertFalse(game.gameOver());

        game.roll(10);
        Assert.assertFalse(game.gameOver());

        game.roll(10);
        Assert.assertFalse(game.gameOver());

        game.roll(10);
        Assert.assertFalse(game.gameOver());

        game.roll(10);
        Assert.assertFalse(game.gameOver());

        game.roll(10);
        Assert.assertFalse(game.gameOver());

        game.roll(10);
        Assert.assertFalse(game.gameOver());

        game.roll(10);
        Assert.assertFalse(game.gameOver());

        game.roll(10);
        Assert.assertFalse(game.gameOver());

        game.roll(10);
        Assert.assertTrue(game.gameOver());

        Assert.assertEquals(300, game.score());
    }

    @Test
    public void the292() throws BowlingException {
        Game game = new Game();

        game.roll(10);
        Assert.assertFalse(game.gameOver());

        game.roll(10);
        Assert.assertFalse(game.gameOver());

        game.roll(10);
        Assert.assertFalse(game.gameOver());

        game.roll(10);
        Assert.assertFalse(game.gameOver());

        game.roll(10);
        Assert.assertFalse(game.gameOver());

        game.roll(10);
        Assert.assertFalse(game.gameOver());

        game.roll(10);
        Assert.assertFalse(game.gameOver());

        game.roll(10);
        Assert.assertFalse(game.gameOver());

        game.roll(10);
        Assert.assertFalse(game.gameOver());

        game.roll(10);
        Assert.assertFalse(game.gameOver());

        game.roll(10);
        Assert.assertFalse(game.gameOver());

        game.roll(2);
        Assert.assertTrue(game.gameOver());

        Assert.assertEquals(292, game.score());
    }

    @Test
    public void strikeThenSpareThenNormal() throws BowlingException {
        Game game = new Game();
        game.roll(10);
        game.roll(1);
        game.roll(9);
        game.roll(5);
        game.roll(4);
        Assert.assertEquals(10 + (1+9) + (1+9) + 5 + 5+4, game.score());
    }

}
