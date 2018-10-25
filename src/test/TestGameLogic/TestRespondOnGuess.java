package test.TestGameLogic;

import main.GameLogic.Game;
import main.GameLogic.GameController;
import org.junit.Test;

public class TestRespondOnGuess
{
    @Test(expected = IllegalArgumentException.class)
    public final void testInvalidLengthAnswer()
    {
        Game game = new Game(4);
        game.respondOnGuess(new int[]{ 1, 2, 3, 4, 5}, game.digitsToGuess.length);
    }
}
