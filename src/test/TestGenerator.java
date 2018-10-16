package test;

import org.junit.Test;
import org.junit.Assert;
import main.GameLogic.SampleGenerator;


public class TestGenerator {
	private static boolean checkUniqueDigits(int [] sampleDigits)
	{
		int digits = 10;
		int[] count = new int[digits];
		for(int i = 0; i < digits; i++)
			count[i] = 0;
		for(int i = 0; i < sampleDigits.length; i++)
			count[sampleDigits[i]]++;
		for(int i = 0; i < digits; i++)
		{
			if (count[i] > 1)
				return false;
		}
		return true;
	}
	
	@Test
    public final void testCorrectGeneration()
    {
		int amountOfDigits = 4;
		int [] sample = SampleGenerator.setDigitsToGuess(amountOfDigits);
        Assert.assertTrue(checkUniqueDigits(sample));
    }
}
