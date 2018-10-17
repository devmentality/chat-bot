package test;

import org.junit.Assert;
import org.junit.Test;

import main.GameLogic.Game;
import main.GameLogic.GameController;
import main.GameLogic.GuessResult;

public class TestAnswer
{
	private GuessResult getResult(int[] guess, int[] digitsToGuess)
	{
		Game game = new Game(digitsToGuess);
		return game.respondOnGuess(guess);
	}

	@Test
    public final void testCorrectOneEqual()
    {	
		GuessResult result = getResult(new int[] {1, 2, 3, 4}, new int[] {1, 5, 6, 7});
		Assert.assertEquals(new GuessResult(1, 0), result);
    }
	
	@Test
    public final void testCorrectTwoEqual()
    {	
		GuessResult result = getResult(new int[] {1, 2, 3, 4}, new int[] {1, 2, 6, 7});
		Assert.assertEquals(new GuessResult(2, 0), result);
		result = getResult(new int[] {1, 2, 3, 4}, new int[] {1, 6, 2, 7});
		Assert.assertEquals(new GuessResult(1, 1), result);
    }
	
	@Test
    public final void testCorrectThreeEqual()
    {		
		GuessResult result = getResult(new int[] {1, 2, 3, 4}, new int[] {1, 2, 3, 8});
		Assert.assertEquals(new GuessResult(3, 0), result);
		result = getResult(new int[] {1, 2, 3, 4}, new int[] {1, 2, 8, 3});
		Assert.assertEquals(new GuessResult(2, 1), result);
		result = getResult(new int[] {1, 2, 3, 4}, new int[] {1, 8, 2, 3});
		Assert.assertEquals(new GuessResult(1, 2), result);
		result = getResult(new int[] {1, 2, 3, 4}, new int[] {8, 1, 2, 3});
		Assert.assertEquals(new GuessResult(0, 3), result);
	}
	
	@Test
    public final void testCorrectFourEqual()
    {		
		GuessResult result = getResult(new int[] {1, 2, 3, 4}, new int[] {1, 2, 3, 4});
		Assert.assertEquals(new GuessResult(4, 0), result);
		result = getResult(new int[] {1, 2, 3, 4}, new int[] {1, 2, 4, 3});
		Assert.assertEquals(new GuessResult(2, 2), result);
		result = getResult(new int[] {1, 2, 3, 4}, new int[] {2, 3, 1, 4});
		Assert.assertEquals(new GuessResult(1, 3), result);
		result = getResult(new int[] {1, 2, 3, 4}, new int[] {4, 3, 2, 1});
		Assert.assertEquals(new GuessResult(0, 4), result);
	}
}
