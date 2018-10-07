package main.Commands;

import main.Data.IAppRepository;
import main.States.GameIsOnState;
import main.GameLogic.Game;
import main.IO.IMessageWriter;
import main.IStateMachine;
import main.Resources.Strings;
import main.Session;

public class ContinueGameCommand extends CommandBase
{
    private Session session;

    public ContinueGameCommand(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer, Session session)
    {
        super(stateMachine, repository, writer, "continue");
        this.session = session;
    }

    @Override
    public void execute()
    {
        if (!repository.hasUnfinishedGame(session.getUsername()))
        {
            writer.write(Strings.noSavedGames);
            return;
        }

        writer.write(Strings.continueGamePhrase);
        Game game = repository.getUnfinishedGame(session.getUsername());
        stateMachine.changeState(new GameIsOnState(stateMachine, repository, writer, game, session));
    }
}
