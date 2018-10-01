package main.Commands;

import main.Data.IAppRepository;
import main.GameLogic.Game;
import main.GameIsOnState;
import main.IO.IMessageWriter;
import main.IStateMachine;
import main.Session;

public class NewGameCommand extends CommandBase
{
    private Session session;

    public NewGameCommand(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer, Session session)
    {
        super(stateMachine, repository, writer, "newgame");
        this.session = session;
    }

    @Override
    public void execute()
    {
        stateMachine.changeState(new GameIsOnState(stateMachine, repository, writer, new Game(), session));
    }
}
