package main;

import main.Data.IAppRepository;
import main.IO.IMessageWriter;
import main.Resources.Strings;

public class StartedState extends StateBase
{
    public StartedState(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer)
    {
        super(stateMachine, repository, writer);
    }

    @Override
    public void activate()
    {
        writer.write(Strings.nameRequest);
    }

    @Override
    public void processRequest(String request)
    {
        String name = request;
        Session session = new Session(name);
        if (repository.hasUser(name))
            writer.write(String.format(Strings.greetingOldUser, name));
        else
        {
            writer.write(String.format(Strings.greetingNewUser, name));
            repository.addUser(name);
        }
        stateMachine.changeState(new InitializedState(stateMachine, repository, writer, session));
    }
}
