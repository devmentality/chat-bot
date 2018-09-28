package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import main.Game;
import main.GameController;
import main.GuessResult;

public class TestAnswer {
	@Test
    public final void testCorrectAnswer()
    {
        int [] answer = GameController.parseGuess("1234");
        assert(answer.length == 4);
        for(int i = 0; i < 4; i++)
        	assert(answer[i] >= 0 && answer[i] <= 9);
    }
	
	@Test(expected = NumberFormatException.class)
    public final void testIncorrectAnswer()
    {
		GameController.parseGuess("123a");
	}
	
	@Test
    public final void testExistsEqualsDigits()
    {	
		int [] answer = GameController.parseGuess("1233");
		assertFalse(TestGenerator.checkUniqueDigits(answer));
	}
	
	private GuessResult getResult(int[] guess, int[] digitsToGuess)
	{
		Game game = new Game(digitsToGuess);
		return game.respondOnGuess(guess);
	}
	
	@Test
    public final void testCorrectOneEqual()
    {	
		GuessResult result = getResult(new int[] {1, 2, 3, 4}, new int[] {1, 5, 6, 7});
		assertEquals(result.amountOfBulls, 1);
		assertEquals(result.amountOfCows, 0);
		result = getResult(new int[] {1, 2, 3, 4}, new int[] {5, 1, 6, 7});
		assertEquals(result.amountOfBulls, 0);
		assertEquals(result.amountOfCows, 1);
		result = getResult(new int[] {1, 2, 3, 4}, new int[] {5, 6, 1, 7});
		assertEquals(result.amountOfBulls, 0);
		assertEquals(result.amountOfCows, 1);
		result = getResult(new int[] {1, 2, 3, 4}, new int[] {5, 6, 7, 1});
		assertEquals(result.amountOfBulls, 0);
		assertEquals(result.amountOfCows, 1);
    }
	
	@Test
    public final void testCorrectTwoEqual()
    {	
		GuessResult result = getResult(new int[] {1, 2, 3, 4}, new int[] {1, 2, 6, 7});
		assertEquals(result.amountOfBulls, 2);
		assertEquals(result.amountOfCows, 0);
		result = getResult(new int[] {1, 2, 3, 4}, new int[] {1, 6, 2, 7});
		assertEquals(result.amountOfBulls, 1);
		assertEquals(result.amountOfCows, 1);
		result = getResult(new int[] {1, 2, 3, 4}, new int[] {6, 1, 2, 7});
		assertEquals(result.amountOfBulls, 0);
		assertEquals(result.amountOfCows, 2);
		result = getResult(new int[] {1, 2, 3, 4}, new int[] {6, 7, 1, 2});
		assertEquals(result.amountOfBulls, 0);
		assertEquals(result.amountOfCows, 2);
    }
	
	@Test
    public final void testCorrectThreeEqual()
    {		
		GuessResult result = getResult(new int[] {1, 2, 3, 4}, new int[] {1, 2, 3, 8});
		assertEquals(result.amountOfBulls, 3);
		assertEquals(result.amountOfCows, 0);
		result = getResult(new int[] {1, 2, 3, 4}, new int[] {1, 2, 8, 3});
		assertEquals(result.amountOfBulls, 2);
		assertEquals(result.amountOfCows, 1);
		result = getResult(new int[] {1, 2, 3, 4}, new int[] {1, 8, 2, 3});
		assertEquals(result.amountOfBulls, 1);
		assertEquals(result.amountOfCows, 2);
		result = getResult(new int[] {1, 2, 3, 4}, new int[] {8, 1, 2, 3});
		assertEquals(result.amountOfBulls, 0);
		assertEquals(result.amountOfCows, 3);
	}
	
	@Test
    public final void testCorrectFourEqual()
    {		
		GuessResult result = getResult(new int[] {1, 2, 3, 4}, new int[] {1, 2, 3, 4});
		assertEquals(result.amountOfBulls, 4);
		assertEquals(result.amountOfCows, 0);
		result = getResult(new int[] {1, 2, 3, 4}, new int[] {1, 2, 4, 3});
		assertEquals(result.amountOfBulls, 2);
		assertEquals(result.amountOfCows, 2);
		result = getResult(new int[] {1, 2, 3, 4}, new int[] {1, 3, 2, 4});
		assertEquals(result.amountOfBulls, 2);
		assertEquals(result.amountOfCows, 2);
		result = getResult(new int[] {1, 2, 3, 4}, new int[] {1, 3, 4, 2});
		assertEquals(result.amountOfBulls, 1);
		assertEquals(result.amountOfCows, 3);
		result = getResult(new int[] {1, 2, 3, 4}, new int[] {2, 3, 1, 4});
		assertEquals(result.amountOfBulls, 1);
		assertEquals(result.amountOfCows, 3);
		result = getResult(new int[] {1, 2, 3, 4}, new int[] {4, 3, 2, 1});
		assertEquals(result.amountOfBulls, 0);
		assertEquals(result.amountOfCows, 4);
	}
}
