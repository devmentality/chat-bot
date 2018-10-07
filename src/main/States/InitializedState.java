package main.States;

import main.Commands.*;
import main.Data.IAppRepository;
import main.IO.IMessageWriter;
import main.IStateMachine;
import main.Session;


public class InitializedState extends StateBase
{
    private Session session;

    public InitializedState(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer, Session session)
    {
        super(stateMachine, repository, writer);
        this.session = session;
        commands = new ICommand[]
        {
                new ExitCommand(stateMachine, repository, writer),
                new HelpCommand(stateMachine, repository, writer),
                new NewGameCommand(stateMachine, repository, writer, session),
                new StatisticsCommand(stateMachine, repository, writer, session),
                new ContinueGameCommand(stateMachine, repository, writer, session)
        };
    }
}
