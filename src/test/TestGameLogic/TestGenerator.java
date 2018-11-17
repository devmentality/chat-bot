package test.TestGameLogic;

import main.GameLogic.GameController;
import org.junit.Test;
import org.junit.Assert;
import main.GameLogic.SampleGenerator;


public class TestGenerator {
	@Test
    public final void testCorrectGeneration()
    {
		int amountOfDigits = 4;
		int attempts = 20;
		for(int i = 0; i < attempts; i++)
		{
			int [] sample = SampleGenerator.setDigitsToGuess(amountOfDigits);
			Assert.assertTrue(GameController.checkUniqueDigits(sample));
		}
    }
}
