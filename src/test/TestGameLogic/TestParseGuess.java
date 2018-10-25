package test.TestGameLogic;

import main.GameLogic.Game;
import main.GameLogic.GameController;
import org.junit.Assert;
import org.junit.Test;

public class TestParseGuess
{
    @Test
    public final void testValidAnswer()
    {
        int[] expectedAnswer = new int[] {1, 2, 3, 4};
        int[] answer = GameController.parseGuess("1234");

        Assert.assertArrayEquals(expectedAnswer, answer);
    }

    @Test(expected = NumberFormatException.class)
    public final void testInvalidAnswer()
    {
        GameController.parseGuess("123a");
    }

}
