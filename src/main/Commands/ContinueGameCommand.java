package main.Commands;

import main.Data.IAppRepository;
import main.States.GameIsOnState;
import main.GameLogic.Game;
import main.IO.IMessageWriter;
import main.IStateMachine;
import main.Resources.Strings;
import main.Session;

import java.util.ArrayList;
import java.util.List;

public class ContinueGameCommand extends CommandBase
{
    private Session session;

    public ContinueGameCommand(IStateMachine stateMachine, IAppRepository repository, Session session)
    {
        super(stateMachine, repository, "continue");
        this.session = session;
    }

    @Override
    public ArrayList<String> execute(String... value)
    {
        if (!repository.hasUnfinishedGame(session.getUsername()))
            return constructOutput(Strings.noSavedGames);

        Game game = repository.getUnfinishedGame(session.getUsername());
        stateMachine.changeState(new GameIsOnState(stateMachine, repository, game, session));

        return constructOutput(Strings.continueGamePhrase);
    }
}
