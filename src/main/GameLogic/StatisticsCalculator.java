package main.GameLogic;

import java.util.ArrayList;

public class StatisticsCalculator
{
    public static Statistics calculate(ArrayList<GameResult> results)
    {
        int victories = 0;
        int losses = 0;
        for (GameResult result: results)
        {
            if (result.isVictory())
                victories++;
            else
                losses++;
        }

        return new Statistics(results.size(), victories, losses);
    }
}
