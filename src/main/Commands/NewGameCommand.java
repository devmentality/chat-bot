package main.Commands;

import main.Data.INewRepository;
import main.Data.User;
import main.GameLogic.Game;
import main.PlainResponse;
import main.Response;
import main.States.GameIsOnState;
import main.IStateMachine;
import main.Resources.Strings;

import java.util.ArrayList;

public class NewGameCommand extends CommandBase
{
    public NewGameCommand(IStateMachine stateMachine, INewRepository repository)
    {
        super(stateMachine, repository, "newgame");
    }

    @Override
    public ArrayList<Response> execute(User user, String... value)
    {
        int number = 4;
        if (value.length == 0)      //Create new game with 4 digits on request "newgame"
        {
            user.unfinishedGame = new Game(number);
            stateMachine.changeState(new GameIsOnState(stateMachine, repository));
            return Response.compose(new PlainResponse(user.id, String.format(Strings.newGamePhrase, number)));
        }
        number = Integer.parseInt(value[0]);        //try to create game with value[0] digits
        if (number < 1 || number > 10)
            throw new IllegalArgumentException();
        user.unfinishedGame = new Game(number);
        stateMachine.changeState(new GameIsOnState(stateMachine, repository));
        return Response.compose(new PlainResponse(user.id, String.format(Strings.newGamePhrase, number)));
    }
}
