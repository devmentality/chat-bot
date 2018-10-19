package main.States;

import main.Commands.ICommand;
import main.Data.IAppRepository;
import main.IO.IMessageWriter;

import java.io.Console;

import main.IState;
import main.IStateMachine;

public abstract class StateBase implements IState
{
    protected IAppRepository repository;
    protected IStateMachine stateMachine;
    protected IMessageWriter writer;
    protected ICommand[] commands;

    public StateBase(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer)
    {
        this.stateMachine = stateMachine;
        this.repository = repository;
        this.writer = writer;
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
    public void processRequest(String request)
    {
        for(ICommand command: commands)
        {   
            if (request.equals(command.getName()))
            {
                command.execute();
                return;
            }
            
            String[] requestParts = request.split(" ");
            if (requestParts[0].equals(command.getName()))  //check newgame command with number
            {
            	if (isCorrectRequest(requestParts))
            	{
            		command.execute(requestParts[1]);
            		return;
            	}
            }
        }
        handleNoncommandRequest(request);
    }

    @Override
    public void activate() { }

    protected void handleNoncommandRequest(String request)
    {
        writer.write("I don't understand:(");
    }
}
