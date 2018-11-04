package main.Commands;

import main.Data.INewRepository;
import main.Data.User;
import main.IStateMachine;
import main.Response;
import main.SelectorResponse;
import main.States.ChooseGameModeState;

import java.util.ArrayList;

public class StartGameCommand extends CommandBase
{
    public StartGameCommand(IStateMachine stateMachine, INewRepository repository)
    {
        super(stateMachine, repository, "startgame");
    }

    @Override
    public ArrayList<Response> execute(User user, String... args)
    {
        Response modeSelector = new SelectorResponse(
                user.id, "Now choose game mode:","classic", "challenge");

        stateMachine.changeState(new ChooseGameModeState(stateMachine, repository));
        return Response.compose(modeSelector);
    }
}
