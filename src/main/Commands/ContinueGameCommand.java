package main.Commands;

import main.Data.IAppRepository;
import main.GameIsOnState;
import main.GameLogic.Game;
import main.IO.IMessageWriter;
import main.IStateMachine;
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
            writer.write("You don't have unfinished games");
            return;
        }

        Game game = repository.getUnfinishedGame(session.getUsername());
        writer.write("Your guesses were: ");
        writer.write("...");
        stateMachine.changeState(new GameIsOnState(stateMachine, repository, writer, game, session));
    }
}
