package main.States;

import main.*;
import main.Data.INewRepository;
import main.Data.User;
import main.Resources.Strings;

import java.util.ArrayList;

public class ChooseGameModeState extends StateBase
{
    public ChooseGameModeState(IStateMachine stateMachine, INewRepository repository)
    {
        super(stateMachine, repository);
    }

    @Override
    public ArrayList<Response> handleNoncommandRequest(User user, String query)
    {
        if (query.equals("classic"))
        {
            SelectorResponse difficultySelector = new SelectorResponse(user.id, "Choose difficulty");
            difficultySelector.addOption("easy");
            difficultySelector.addOption("medium");
            difficultySelector.addOption("hard");

            stateMachine.changeState(new ChooseGameDifficultyState(stateMachine, repository));
            return Response.compose(difficultySelector);
        }
        stateMachine.changeState(new InitializedState(stateMachine, repository));
        return Response.compose(new PlainResponse(user.id, Strings.iDontUnderstand));
    }
}
