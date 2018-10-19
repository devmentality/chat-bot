package main.Commands;

import main.Data.IAppRepository;
import main.GameLogic.Game;
import main.IO.IMessageWriter;
import main.IStateMachine;
import main.States.InitializedState;
import main.Session;

public class StopGameCommand extends CommandBase
{
    private Session session;
    private Game currentGame;

    public StopGameCommand(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer,
                           Session session, Game currentGame)
    {
        super(stateMachine, repository, writer, "stop");
        this.session = session;
        this.currentGame = currentGame;
    }

    @Override
    public void execute(String... value)
    {
        repository.addUnfinishedGame(session.getUsername(), currentGame);
        stateMachine.changeState(new InitializedState(stateMachine, repository, writer, session));
    }
}
