package main.Commands;

import main.Data.INewRepository;
import main.Data.User;
import main.States.GameIsOnState;
import main.GameLogic.Game;
import main.IStateMachine;
import main.Resources.Strings;

import java.util.ArrayList;

public class ContinueGameCommand extends CommandBase
{
    public ContinueGameCommand(IStateMachine stateMachine, INewRepository repository)
    {
        super(stateMachine, repository,"continue");
    }

    @Override
    public ArrayList<String> execute(User user, String... value)
    {
        if (user.unfinishedGame == null)
            return constructOutput(Strings.noSavedGames);

        stateMachine.changeState(new GameIsOnState(stateMachine, repository));
        return constructOutput(Strings.continueGamePhrase);
    }
}
