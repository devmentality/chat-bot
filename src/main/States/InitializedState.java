package main.States;

import main.Commands.*;
import main.Data.INewRepository;
import main.Data.User;
import main.IStateMachine;


public class InitializedState extends StateBase
{
    public InitializedState(IStateMachine stateMachine, INewRepository repository)
    {
        super(stateMachine, repository);
        commands = new ICommand[]
        {
                new HelpCommand(stateMachine, repository),
                new NewGameCommand(stateMachine, repository),
                new StatisticsCommand(stateMachine, repository),
                new ContinueGameCommand(stateMachine, repository)
        };
    }
}
