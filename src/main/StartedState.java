package main;

import main.Data.IAppRepository;
import main.IO.IMessageWriter;

public class StartedState extends StateBase
{
    public StartedState(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer)
    {
        super(stateMachine, repository, writer);
    }

    @Override
    public void processRequest(String request)
    {
        String name = request;
        if (repository.hasUser(name))
            writer.write(String.format("Hi, my old friend, %s", name));
        else
        {
            writer.write(String.format("Hello, %s", name));
            repository.addUser(name);
        }
        stateMachine.changeState(new InitializedState(stateMachine, repository, writer));
    }
}
