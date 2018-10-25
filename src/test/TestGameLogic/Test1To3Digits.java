package test.TestGameLogic;

import main.GameLogic.Game;
import main.GameLogic.GuessResult;
import org.junit.Assert;
import org.junit.Test;

public class Test1To3Digits
{
    private GuessResult getResult(int[] guess, int[] digitsToGuess)
    {
        Game game = new Game(digitsToGuess);
        return game.respondOnGuess(guess, digitsToGuess.length);
    }

    @Test
    public final void test1Digit()
    {
        GuessResult result = getResult(new int[] {1}, new int[] {1});
        Assert.assertEquals(new GuessResult(1, 0), result);
        result = getResult(new int[] {1}, new int[] {0});
        Assert.assertEquals(new GuessResult(0, 0), result);
    }

    @Test
    public final void test2Digits()
    {
        GuessResult result = getResult(new int[] {1, 2}, new int[] {0, 3});
        Assert.assertEquals(new GuessResult(0, 0), result);
        result = getResult(new int[] {1, 2}, new int[] {2, 0});
        Assert.assertEquals(new GuessResult(0, 1), result);
        result = getResult(new int[] {1, 2}, new int[] {1, 0});
        Assert.assertEquals(new GuessResult(1, 0), result);
        result = getResult(new int[] {1, 2}, new int[] {1, 2});
        Assert.assertEquals(new GuessResult(2, 0), result);
    }

    @Test
    public final void test3Digits()
    {
        GuessResult result = getResult(new int[] {1, 2, 3}, new int[] {0, 4, 5});
        Assert.assertEquals(new GuessResult(0, 0), result);
        result = getResult(new int[] {1, 2, 3}, new int[] {0, 1, 4});
        Assert.assertEquals(new GuessResult(0, 1), result);
        result = getResult(new int[] {1, 2, 3}, new int[] {2, 1, 0});
        Assert.assertEquals(new GuessResult(0, 2), result);
        result = getResult(new int[] {1, 2, 3}, new int[] {2, 3, 1});
        Assert.assertEquals(new GuessResult(0, 3), result);
        result = getResult(new int[] {1, 2, 3}, new int[] {1, 4, 5});
        Assert.assertEquals(new GuessResult(1, 0), result);
        result = getResult(new int[] {1, 2, 3}, new int[] {1, 3, 4});
        Assert.assertEquals(new GuessResult(1, 1), result);
        result = getResult(new int[] {1, 2, 3}, new int[] {1, 3, 2});
        Assert.assertEquals(new GuessResult(1, 2), result);
        result = getResult(new int[] {1, 2, 3}, new int[] {1, 2, 4});
        Assert.assertEquals(new GuessResult(2, 0), result);
        result = getResult(new int[] {1, 2, 3}, new int[] {1, 2, 3});
        Assert.assertEquals(new GuessResult(3, 0), result);
    }
}
