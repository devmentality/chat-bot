package main.Commands;

import main.Data.IAppRepository;
import main.GameLogic.Game;
import main.States.GameIsOnState;
import main.IO.IMessageWriter;
import main.IStateMachine;
import main.Resources.Strings;
import main.Session;

public class NewGameCommand extends CommandBase
{
    private Session session;

    public NewGameCommand(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer, Session session)
    {
        super(stateMachine, repository, writer, "newgame");
        this.session = session;
    }

    @Override
    public void execute(String... value)
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
        writer.write(String.format(Strings.newGamePhrase, number));
        stateMachine.changeState(new GameIsOnState(stateMachine, repository, writer, new Game(number), session));
    }
}
