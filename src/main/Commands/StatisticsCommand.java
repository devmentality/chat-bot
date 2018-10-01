package main.Commands;

import main.Data.IAppRepository;
import main.GameLogic.GameResult;
import main.IO.IMessageWriter;
import main.IStateMachine;
import main.Session;

import java.util.ArrayList;

public class StatisticsCommand extends CommandBase
{
    private Session session;

    public StatisticsCommand(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer, Session session)
    {
        super(stateMachine, repository, writer, "stat");
        this.session = session;
    }

    @Override
    public void execute()
    {
        ArrayList<GameResult> results = repository.getGameResults(session.getUsername());
        int victories = 0;
        int losses = 0;
        for (GameResult result: results)
        {
            if (result.isVictory())
                victories++;
            else
                losses++;
        }

        writer.write(String.format("%d games played %d victories %d losses", victories + losses,
                victories, losses));
    }
}
