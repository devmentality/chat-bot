package main.Commands;

import main.Data.IAppRepository;
import main.GameLogic.Statistics;
import main.GameLogic.StatisticsCalculator;
import main.IStateMachine;
import main.Resources.Strings;
import main.Session;

import java.util.ArrayList;
import java.util.List;

public class StatisticsCommand extends CommandBase
{
    private Session session;

    public StatisticsCommand(IStateMachine stateMachine, IAppRepository repository, Session session)
    {
        super(stateMachine, repository, "stat");
        this.session = session;
    }

    @Override
    public ArrayList<String> execute(String... value)
    {
        Statistics stat = StatisticsCalculator.calculate(
                repository.getGameResults(session.getUsername()));

        return constructOutput(String.format(Strings.statisticsTemplate, stat.getTotal(),
                stat.getVictories(), stat.getLosses()));
    }
}
