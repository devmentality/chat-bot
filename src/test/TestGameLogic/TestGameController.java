package test.TestGameLogic;

import main.GameLogic.GameController;
import org.junit.Assert;
import org.junit.Test;

public class TestGameController
{
    @Test
    public final void parseGuess_shouldParseDigitsProperly()
    {
        String input = "12345";
        int[] expected = new int[]{1, 2, 3, 4, 5};
        int[] actual = GameController.parseGuess(input);

        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public final void checkUniqueDigits_shouldReturnTrue_whenAllUnique()
    {
        int[] input = new int[]{1, 2, 3, 4, 5};
        Assert.assertTrue(GameController.checkUniqueDigits(input));
    }

    @Test
    public final void checkUniqueDigits_shouldReturnFalse_whenNotUnique()
    {
        int[] input = new int[]{1, 2, 3, 4, 2};
        Assert.assertFalse(GameController.checkUniqueDigits(input));
    }
}
