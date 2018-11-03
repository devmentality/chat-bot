package main.Commands;

import main.Data.INewRepository;
import main.Data.User;
import main.GameLogic.GameResult;
import main.IStateMachine;
import main.Resources.Strings;
import main.States.InitializedState;

import java.util.ArrayList;
import java.util.Arrays;

public class ResignCommand extends CommandBase
{
    public ResignCommand(IStateMachine stateMachine, INewRepository repository)
    {
        super(stateMachine, repository, "resign");
    }

    @Override
    public ArrayList<String> execute(User user, String... value)
    {
        user.gameResults.add(new GameResult(false));
        stateMachine.changeState(new InitializedState(stateMachine, repository));
        return new ArrayList<>(Arrays.asList(Strings.onGameResign));
    }
}
