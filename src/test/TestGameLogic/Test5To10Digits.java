package test.TestGameLogic;

import main.GameLogic.Game;
import main.GameLogic.GuessResult;
import org.junit.Assert;
import org.junit.Test;

public class Test5To10Digits
{
    private GuessResult getResult(int[] guess, int[] digitsToGuess)
    {
        Game game = new Game(digitsToGuess);
        return game.respondOnGuess(guess, digitsToGuess.length);
    }

    @Test
    public final void test5Digits()
    {
        GuessResult result = getResult(new int[] {1, 2, 3, 4, 5}, new int[] {1, 6, 7, 8, 9});
        Assert.assertEquals(new GuessResult(1, 0), result);
        result = getResult(new int[] {1, 2, 3, 4, 5}, new int[] {1, 2, 7, 8, 9});
        Assert.assertEquals(new GuessResult(2, 0), result);
        result = getResult(new int[] {1, 2, 3, 4, 5}, new int[] {1, 2, 3, 8, 9});
        Assert.assertEquals(new GuessResult(3, 0), result);
        result = getResult(new int[] {1, 2, 3, 4, 5}, new int[] {1, 2, 3, 4, 9});
        Assert.assertEquals(new GuessResult(4, 0), result);
        result = getResult(new int[] {1, 2, 3, 4, 5}, new int[] {1, 2, 3, 4, 5});
        Assert.assertEquals(new GuessResult(5, 0), result);
    }

    @Test
    public final void test6Digits()
    {
        GuessResult result = getResult(new int[] {1, 2, 3, 4, 5, 6}, new int[] {1, 6, 7, 8, 9, 0});
        Assert.assertEquals(new GuessResult(1, 1), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6}, new int[] {1, 2, 4, 3, 8, 9});
        Assert.assertEquals(new GuessResult(2, 2), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6}, new int[] {1, 2, 3, 6, 4, 5});
        Assert.assertEquals(new GuessResult(3, 3), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6}, new int[] {1, 2, 3, 4, 6, 5});
        Assert.assertEquals(new GuessResult(4, 2), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6}, new int[] {1, 2, 3, 4, 5, 6});
        Assert.assertEquals(new GuessResult(6, 0), result);
    }

    @Test
    public final void test7Digits()
    {
        GuessResult result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7}, new int[] {1, 6, 7, 8, 9, 0, 2});
        Assert.assertEquals(new GuessResult(1, 3), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7}, new int[] {1, 2, 4, 3, 8, 9, 0});
        Assert.assertEquals(new GuessResult(2, 2), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7}, new int[] {1, 2, 3, 6, 4, 5, 0});
        Assert.assertEquals(new GuessResult(3, 3), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7}, new int[] {1, 2, 3, 4, 6, 5, 0});
        Assert.assertEquals(new GuessResult(4, 2), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7}, new int[] {1, 2, 3, 4, 5, 6, 0});
        Assert.assertEquals(new GuessResult(6, 0), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7}, new int[] {1, 2, 3, 4, 5, 6, 7});
        Assert.assertEquals(new GuessResult(7, 0), result);
    }

    @Test
    public final void test8Digits()
    {
        GuessResult result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, new int[] {1, 6, 7, 8, 9, 0, 2, 3});
        Assert.assertEquals(new GuessResult(1, 5), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, new int[] {1, 2, 4, 3, 8, 9, 0, 5});
        Assert.assertEquals(new GuessResult(2, 4), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, new int[] {1, 2, 3, 6, 4, 5, 0, 7});
        Assert.assertEquals(new GuessResult(3, 4), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, new int[] {1, 2, 3, 4, 6, 5, 0, 7});
        Assert.assertEquals(new GuessResult(4, 3), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, new int[] {1, 2, 3, 4, 5, 6, 0, 7});
        Assert.assertEquals(new GuessResult(6, 1), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, new int[] {1, 2, 3, 4, 5, 6, 7, 8});
        Assert.assertEquals(new GuessResult(8, 0), result);
    }

    @Test
    public final void test9Digits()
    {
        GuessResult result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[] {1, 6, 7, 8, 9, 0, 2, 3, 4});
        Assert.assertEquals(new GuessResult(1, 7), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[] {1, 2, 4, 3, 8, 9, 0, 5, 6});
        Assert.assertEquals(new GuessResult(2, 6), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[] {1, 2, 3, 6, 4, 5, 0, 7, 8});
        Assert.assertEquals(new GuessResult(3, 5), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[] {1, 2, 3, 4, 6, 5, 0, 7, 8});
        Assert.assertEquals(new GuessResult(4, 4), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[] {1, 2, 3, 4, 5, 6, 0, 7, 8});
        Assert.assertEquals(new GuessResult(6, 2), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
        Assert.assertEquals(new GuessResult(9, 0), result);
    }

    @Test
    public final void test10Digits()
    {
        GuessResult result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0},
                new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        Assert.assertEquals(new GuessResult(0, 10), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, new int[] {1, 9, 8, 7, 6, 5, 4, 3, 0, 2});
        Assert.assertEquals(new GuessResult(1, 9), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, new int[] {1, 2, 0, 3, 4, 5, 6, 7, 8, 9});
        Assert.assertEquals(new GuessResult(2, 8), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, new int[] {1, 2, 3, 0, 4, 5, 6, 7, 8, 9});
        Assert.assertEquals(new GuessResult(3, 7), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, new int[] {1, 2, 3, 4, 0, 5, 6, 7, 8, 9});
        Assert.assertEquals(new GuessResult(4, 6), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, new int[] {1, 2, 3, 4, 5, 0, 6, 7, 8, 9});
        Assert.assertEquals(new GuessResult(5, 5), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, new int[] {1, 2, 3, 4, 5, 6, 0, 7, 8, 9});
        Assert.assertEquals(new GuessResult(6, 4), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, new int[] {1, 2, 3, 4, 5, 6, 7, 0, 8, 9});
        Assert.assertEquals(new GuessResult(7, 3), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, new int[] {1, 2, 3, 4, 5, 6, 7, 8, 0, 9});
        Assert.assertEquals(new GuessResult(8, 2), result);
        result = getResult(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0});
        Assert.assertEquals(new GuessResult(10, 0), result);

    }
}
