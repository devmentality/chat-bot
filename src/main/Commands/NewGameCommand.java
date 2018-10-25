package main.Commands;

import main.Data.IAppRepository;
import main.GameLogic.Game;
import main.States.GameIsOnState;
import main.IO.IMessageWriter;
import main.IStateMachine;
import main.Resources.Strings;
import main.Session;

import java.util.ArrayList;
import java.util.List;

public class NewGameCommand extends CommandBase
{
    private Session session;

    public NewGameCommand(IStateMachine stateMachine, IAppRepository repository, Session session)
    {
        super(stateMachine, repository, "newgame");
        this.session = session;
    }

    @Override
    public ArrayList<String> execute(String... value)
    {
        int number = 4;
        if (value.length == 0)      //Create new game with 4 digits on request "newgame"
        {
            stateMachine.changeState(new GameIsOnState(stateMachine, repository, new Game(number), session));
            return constructOutput(String.format(Strings.newGamePhrase, number));
        }
        number = Integer.parseInt(value[0]);        //try to create game with value[0] digits
        if (number < 1 || number > 10)
            throw new IllegalArgumentException();
        stateMachine.changeState(new GameIsOnState(stateMachine, repository, new Game(number), session));
        return constructOutput(String.format(Strings.newGamePhrase, number));
    }
}
