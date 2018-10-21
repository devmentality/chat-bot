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
    	int number;									//Create new game with number digits(default - 4)
    	try
    	{
    	    number = Integer.parseInt(value[0]);
    	}
    	catch(ArrayIndexOutOfBoundsException e)
    	{
    		number = 4;
    	}

        stateMachine.changeState(new GameIsOnState(stateMachine, repository, new Game(number), session));
        return constructOutput(String.format(Strings.newGamePhrase, number));
    }
}
