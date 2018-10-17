package test;

import static org.junit.Assume.assumeTrue;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import main.GameLogic.Game;
import main.GameLogic.GuessResult;

@RunWith(Theories.class)
public class TestThreeEqualCows
{
	private GuessResult getResult(int[] guess, int[] digitsToGuess)
	{
		Game game = new Game(digitsToGuess);
		return game.respondOnGuess(guess);
	}
	
	@DataPoints
	public static int[][] attempts()
	{
		return new int[][] {
			{5, 1, 2, 3}, 
			{5, 3, 1, 2}, 
			{5, 3, 2, 1},
			{3, 1, 2, 5}, 
			{3, 1, 5, 2}, 
			{3, 5, 1, 2}, 
			{3, 5, 2, 1},
			{2, 1, 5, 3}, 
			{2, 3, 1, 5}, 
			{2, 3, 5, 1}, 
			{2, 5, 1, 3}
			};
	}
	
	@Theory
	public void testCorrectBullsAndCows(int[] attempt)
	{
		GuessResult result = getResult(new int[] {1, 2, 3, 4}, attempt);
		assumeTrue(new GuessResult(0, 3).equals(result));
	}
}
