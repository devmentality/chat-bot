package main.States;

import main.Bot;
import main.Commands.ICommand;

import java.lang.reflect.Array;
import java.util.ArrayList;
import main.Data.INewRepository;
import main.Data.User;
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
            String[] requestParts = request.split(" ");
            String commandName = requestParts[0];
            String[] args = new String[requestParts.length - 1];
            for(int i = 1; i < requestParts.length; i++)
                args[i - 1] = requestParts[i];

            if (commandName.equals(command.getName()) && command.getAmountOfArgs() == args.length)
                return command.execute(user, args);
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
