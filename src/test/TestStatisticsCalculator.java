package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Random;

import main.GameLogic.GameResult;
import main.GameLogic.Statistics;
import main.GameLogic.StatisticsCalculator;
import org.junit.Test;

public class TestStatisticsCalculator
{
    @Test
    public final void testCalculateStatisticsWithNoGames()
    {
        ArrayList<GameResult> results = new ArrayList<>();
        Statistics stat = StatisticsCalculator.calculate(results);

        assertEquals(0, stat.getTotal());
        assertEquals(0, stat.getVictories());
        assertEquals(0, stat.getLosses());
    }

    @Test
    public final void testCalculateStatisticsRandomized()
    {
        int maxGames = 1000;

    	Random random = new Random();
    	int winGames = random.nextInt(maxGames);
    	int loseGames = random.nextInt(maxGames);
    	ArrayList<GameResult> results = new ArrayList<>();
    	for (int i = 0; i < winGames; i++)
    		results.add(new GameResult(true));
    	for(int i = 0; i < loseGames; i++)
    		results.add(new GameResult(false));

        Statistics stat = StatisticsCalculator.calculate(results);

        assertEquals(winGames, stat.getVictories());
        assertEquals(loseGames, stat.getLosses());
        assertEquals(winGames + loseGames, stat.getTotal());
    }
}
