package test.TestGameLogic;

import static org.junit.Assume.assumeTrue;

import org.junit.Assert;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import main.GameLogic.Game;
import main.GameLogic.GuessResult;

@RunWith(Theories.class)
public class TestOneEqualCow
{
	private GuessResult getResult(int[] guess, int[] digitsToGuess)
	{
		Game game = new Game(digitsToGuess);
		return game.respondOnGuess(guess, digitsToGuess.length);
	}
	
	@DataPoints
	public static int[][] attempts()
	{
		return new int[][] {
			{5, 1, 6, 7}, 
			{5, 1, 7, 6}, 
			{5, 6, 1, 7},
			{5, 6, 7, 1}, 
			{5, 7, 1, 6}, 
			{5, 7, 6, 1}, 
			{6, 1, 5, 7}, 
			{6, 1, 7, 5},
			{6, 5, 1, 7}, 
			{6, 5, 7, 1}, 
			{6, 7, 1, 5}, 
			{6, 7, 5, 1}, 
			{7, 1, 5, 6},
			{7, 1, 6, 5}, 
			{7, 5, 1, 6}, 
			{7, 5, 6, 1}, 
			{7, 6, 1, 5}, 
			{7, 6, 5, 1}
			};
	}
	
	@Theory
	public void testCorrectBullsAndCows(int[] attempt)
	{
		GuessResult result = getResult(new int[] {1, 2, 3, 4}, attempt);
		assumeTrue(new GuessResult(0, 1).equals(result));
	}
}
