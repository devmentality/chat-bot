package main.Commands;

import main.Bot;
import main.Data.INewRepository;
import main.Data.User;
import main.GameLogic.Statistics;
import main.GameLogic.StatisticsCalculator;
import main.IStateMachine;
import main.PlainResponse;
import main.Resources.Strings;
import main.Response;

import java.util.ArrayList;

public class StatisticsCommand extends CommandBase
{
    public StatisticsCommand(Bot bot, INewRepository repository)
    {
        super(bot, repository, "stat");
    }

    @Override
    public ArrayList<Response> execute(User user, String... value)
    {
        Statistics stat = StatisticsCalculator.calculate(user.gameResults);

        return Response.compose(new PlainResponse(user.id, String.format(Strings.statisticsTemplate, stat.getTotal(),
                stat.getVictories(), stat.getLosses())));
    }
}
