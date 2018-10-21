package main.States;

import main.Commands.ICommand;
import main.Data.IAppRepository;
import main.IO.IMessageWriter;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.IState;
import main.IStateMachine;

public abstract class StateBase implements IState
{
    protected IAppRepository repository;
    protected IStateMachine stateMachine;
    protected ICommand[] commands;

    public StateBase(IStateMachine stateMachine, IAppRepository repository)
    {
        this.stateMachine = stateMachine;
        this.repository = repository;
    }

    private boolean isCorrectRequest(String[] value)
    {
    	try
    	{
    		Integer.parseInt(value[1]);
    	}
    	catch(NumberFormatException | ArrayIndexOutOfBoundsException e)
    	{
    		return false;
    	}
    	int number = Integer.parseInt(value[1]);
    	return number >= 1 && number <= 10 && value.length == 2;
    }
    
    @Override
    public ArrayList<String> processRequest(String request)
    {
        for(ICommand command: commands)
        {   
            if (request.equals(command.getName()))
                return command.execute();
            
            String[] requestParts = request.split(" ");
            if (requestParts[0].equals(command.getName()))  //check newgame command with number
            {
            	if (isCorrectRequest(requestParts))
            		return command.execute(requestParts[1]);
            }
        }
        return handleNoncommandRequest(request);
    }

    protected ArrayList<String> handleNoncommandRequest(String request)
    {
        return new ArrayList<>(Arrays.asList("I don't understand:("));
    }
}
