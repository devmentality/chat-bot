package main;

public class Bot
{
    private IMessageReader reader;
    private IMessageWriter writer;
    private IState state;

    public Bot(IMessageReader reader, IMessageWriter writer, IState state)
    {
        this.reader = reader;
        this.writer = writer;
        this.writer.write(setAndInitializeState(state));
    }

    public String setAndInitializeState(IState newState)
    {
        state = newState;
        state.setContext(this);
        return state.initialize();
    }

    public void execute()
    {
        while(true)
        {
            String request = reader.read();
            writer.write(state.respond(request));
            if (request.equals("exit"))
                break;
        }
    }
}
