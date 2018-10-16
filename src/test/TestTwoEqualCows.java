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
public class TestTwoEqualCows
{
	private GuessResult getResult(int[] guess, int[] digitsToGuess)
	{
		Game game = new Game(digitsToGuess);
		return game.respondOnGuess(guess);
	}
	
	@DataPoints
	public static int[][] attempts()
	{
		return new int[][] {{2, 1, 5, 6}, {2, 1, 6, 5}, {2, 5, 1, 6},
			{2, 5, 6, 1}, {2, 6, 1, 5}, {2, 6, 5, 1}, {5, 1, 2, 6}, {5, 1, 6, 2},
			{5, 6, 1, 2}, {5, 6, 2, 1}, {6, 1, 2, 5}, {6, 1, 5, 2}, {6, 5, 1, 2},
			{6, 5, 2, 1}};
	}
	
	@Theory
	public void testCorrectBullsAndCows(int[] attempt)
	{
		GuessResult result = getResult(new int[] {1, 2, 3, 4}, attempt);
		assumeTrue(new GuessResult(0, 2).equals(result));
	}
}
