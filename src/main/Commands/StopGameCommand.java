package main.Commands;

import main.Data.INewRepository;
import main.Data.User;
import main.GameLogic.Game;
import main.IStateMachine;
import main.Resources.Strings;
import main.States.InitializedState;
import java.util.ArrayList;
import java.util.Arrays;

public class StopGameCommand extends CommandBase
{
    public StopGameCommand(IStateMachine stateMachine, INewRepository repository)
    {
        super(stateMachine, repository, "stop");
    }

    @Override
    public ArrayList<String> execute(User user, String... value)
    {
        stateMachine.changeState(new InitializedState(stateMachine, repository));
        return new ArrayList<>(Arrays.asList(Strings.onGameStop));
    }
}
