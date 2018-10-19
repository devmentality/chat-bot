package main.Commands;

import main.Data.IAppRepository;
import main.GameLogic.GameResult;
import main.GameLogic.Statistics;
import main.GameLogic.StatisticsCalculator;
import main.IO.IMessageWriter;
import main.IStateMachine;
import main.Resources.Strings;
import main.Session;

import java.util.ArrayList;

public class StatisticsCommand extends CommandBase
{
    private Session session;

    public StatisticsCommand(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer, Session session)
    {
        super(stateMachine, repository, writer, "stat");
        this.session = session;
    }

    @Override
    public void execute(String... value)
    {
        Statistics stat = StatisticsCalculator.calculate(
                repository.getGameResults(session.getUsername()));

        writer.write(String.format(Strings.statisticsTemplate, stat.getTotal(),
                stat.getVictories(), stat.getLosses()));
    }
}
