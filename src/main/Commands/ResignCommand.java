package main.Commands;

import main.Data.IAppRepository;
import main.GameLogic.GameResult;
import main.IStateMachine;
import main.Resources.Strings;
import main.States.InitializedState;
import main.Session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResignCommand extends CommandBase
{
    private Session session;

    public ResignCommand(IStateMachine stateMachine, IAppRepository repository, Session session)
    {
        super(stateMachine, repository, "resign");
        this.session = session;
    }

    @Override
    public ArrayList<String> execute(String... value)
    {
        repository.addGameResult(session.getUsername(), new GameResult(false));
        stateMachine.changeState(new InitializedState(stateMachine, repository, session));
        return new ArrayList<>(Arrays.asList(Strings.onGameResign));
    }
}
