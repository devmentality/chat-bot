package main;

import main.Data.IAppRepository;
import main.IO.IMessageWriter;


public class InitializedState extends StateBase
{
    private ICommand[] commands;

    public InitializedState(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer)
    {
        super(stateMachine, repository, writer);

        commands = new ICommand[]
        {
            new ExitCommand(stateMachine, repository, writer),
            new HelpCommand(stateMachine, repository, writer)
        };
    }

    @Override
    public void processRequest(String request)
    {
        for(ICommand command: commands)
        {
            if (request.equals(command.getName()))
            {
                command.execute();
                break;
            }
        }
    }
}
