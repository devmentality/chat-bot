package main;

import main.Data.IAppRepository;
import main.IO.IMessageReader;
import main.IO.IMessageWriter;

public class Bot implements IStateMachine
{
    private IMessageReader reader;
    private IMessageWriter writer;
    private IAppRepository repository;
    private String userName;
    private IState state;
    private boolean isTerminated;

    public Bot(IMessageReader reader, IMessageWriter writer, IAppRepository repository)
    {
        this.reader = reader;
        this.writer = writer;
        this.repository = repository;
        state = new BeforeStartState(this, repository, writer);
        isTerminated = false;
    }

    public void changeState(IState nextState)
    {
        state = nextState;
    }

    public IState getCurrentState()
    {
        return state;
    }

    public void signalToTerminate()
    {
        isTerminated = true;
    }

    public void setUserName(String name)
    {
        this.userName = name;
    }

    public  String getUserName() {
        return userName;
    }

    public void execute()
    {
        writer.write(getWelcoming());
        while(!isTerminated)
        {
            String request = reader.read();
            state.processRequest(request);
        }
    }

    private String getWelcoming()
    {
        return "Hi, I'm bot. Send 'start' to start dialog";
    }
}
