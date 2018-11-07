package main.States;

import main.Bot;
import main.Commands.*;
import main.Data.ChallengeRepository;
import main.Data.INewRepository;
import main.Data.User;
import main.IStateMachine;


public class InitializedState extends StateBase
{
    public InitializedState(Bot bot, INewRepository repository,
                            ChallengeRepository challengeRepository)
    {
        super(bot, repository);
        commands = new ICommand[]
        {
                new HelpCommand(bot, repository),
                new StartGameCommand(bot, repository),
                new StatisticsCommand(bot, repository),
                new AddChallengeCommand(bot, repository, challengeRepository)
        };
    }
}
