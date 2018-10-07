package main.Commands;

import main.Data.IAppRepository;
import main.GameLogic.GameResult;
import main.IO.IMessageWriter;
import main.IStateMachine;
import main.States.InitializedState;
import main.Session;

public class ResignCommand extends CommandBase
{
    private Session session;

    public ResignCommand(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer, Session session)
    {
        super(stateMachine, repository, writer, "resign");
        this.session = session;
    }

    @Override
    public void execute()
    {
        repository.addGameResult(session.getUsername(), new GameResult(false));
        stateMachine.changeState(new InitializedState(stateMachine, repository, writer, session));
    }
}
