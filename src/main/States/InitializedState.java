package main.States;

import main.Commands.*;
import main.Data.IAppRepository;
import main.IO.IMessageWriter;
import main.IStateMachine;
import main.Session;


public class InitializedState extends StateBase
{
    private Session session;

    public InitializedState(IStateMachine stateMachine, IAppRepository repository, Session session)
    {
        super(stateMachine, repository);
        this.session = session;
        commands = new ICommand[]
        {
                new ExitCommand(stateMachine, repository),
                new HelpCommand(stateMachine, repository),
                new NewGameCommand(stateMachine, repository, session),
                new StatisticsCommand(stateMachine, repository, session),
                new ContinueGameCommand(stateMachine, repository, session)
        };
    }
}
