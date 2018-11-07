package main.States;

import main.Bot;
import main.Commands.ICommand;
import main.Data.IAppRepository;
import java.util.ArrayList;
import java.util.Arrays;

import main.Data.INewRepository;
import main.Data.User;
import main.IStateMachine;
import main.PlainResponse;
import main.Resources.Strings;
import main.Response;

public abstract class StateBase implements IState
{
    protected INewRepository repository;
    protected Bot bot;
    protected ICommand[] commands;

    public StateBase(Bot bot, INewRepository repository)
    {
        this.bot = bot;
        this.repository = repository;
        this.commands = new ICommand[]{};
    }

    
    @Override
    public ArrayList<Response> processRequest(User user, String request)
    {
        for(ICommand command: commands)
        {   
            if (request.equals(command.getName()))
                return command.execute(user);
            
            String[] requestParts = request.split(" ");
            if (requestParts[0].equals(command.getName()))  //check command with number
            {
                try
                {
                    return command.execute(user, requestParts[1]);
                }
                catch(IllegalArgumentException | ArrayIndexOutOfBoundsException e)
                {
                    continue;
                }
            }
        }
        return handleNoncommandRequest(user, request);
    }

    @Override
    public ArrayList<String> getAvailableCommands()
    {
        ArrayList<String> commandsNames = new ArrayList<>();
        for(ICommand command: commands)
            commandsNames.add(command.getName());
        return commandsNames;
    }

    protected ArrayList<Response> handleNoncommandRequest(User user, String request)
    {
        return Response.compose(new PlainResponse(user.id, Strings.iDontUnderstand));
    }
}
