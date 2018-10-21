package main.Commands;

import main.Data.IAppRepository;
import main.GameLogic.Game;
import main.IO.IMessageWriter;
import main.IStateMachine;
import main.States.InitializedState;
import main.Session;

import java.util.ArrayList;
import java.util.List;

public class StopGameCommand extends CommandBase
{
    private Session session;
    private Game currentGame;

    public StopGameCommand(IStateMachine stateMachine, IAppRepository repository,
                           Session session, Game currentGame)
    {
        super(stateMachine, repository, "stop");
        this.session = session;
        this.currentGame = currentGame;
    }

    @Override
    public ArrayList<String> execute(String... value)
    {
        repository.addUnfinishedGame(session.getUsername(), currentGame);
        stateMachine.changeState(new InitializedState(stateMachine, repository, session));
        return new ArrayList<>();
    }
}
