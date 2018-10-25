package main.States;

import main.Commands.ICommand;
import main.Data.IAppRepository;
import java.util.ArrayList;
import java.util.Arrays;

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

    
    @Override
    public ArrayList<String> processRequest(String request)
    {
        for(ICommand command: commands)
        {   
            if (request.equals(command.getName()))
                return command.execute();
            
            String[] requestParts = request.split(" ");
            if (requestParts[0].equals(command.getName()))  //check command with number
            {
                ArrayList <String> output;
                try
                {
                    output = command.execute(requestParts[1]);
                }
                catch(IllegalArgumentException | ArrayIndexOutOfBoundsException e)
                {
                    continue;
                }
                return output;
            }
        }
        return handleNoncommandRequest(request);
    }

    @Override
    public ArrayList<String> getAvailableCommands()
    {
        ArrayList<String> commandsNames = new ArrayList<>();
        for(ICommand command: commands)
            commandsNames.add(command.getName());
        return commandsNames;
    }

    protected ArrayList<String> handleNoncommandRequest(String request)
    {
        return new ArrayList<>(Arrays.asList("I don't understand:("));
    }
}
