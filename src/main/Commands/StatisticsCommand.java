package main.Commands;

import main.Data.INewRepository;
import main.Data.User;
import main.GameLogic.Statistics;
import main.GameLogic.StatisticsCalculator;
import main.IStateMachine;
import main.Resources.Strings;

import java.util.ArrayList;

public class StatisticsCommand extends CommandBase
{
    public StatisticsCommand(IStateMachine stateMachine, INewRepository repository)
    {
        super(stateMachine, repository, "stat");
    }

    @Override
    public ArrayList<String> execute(User user, String... value)
    {
        Statistics stat = StatisticsCalculator.calculate(user.gameResults);

        return constructOutput(String.format(Strings.statisticsTemplate, stat.getTotal(),
                stat.getVictories(), stat.getLosses()));
    }
}
