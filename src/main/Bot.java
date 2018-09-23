package main;

public class Bot
{
    private IMessageReader reader;
    private IMessageWriter writer;
    private IController[] controllers;

    public Bot(IMessageReader reader, IMessageWriter writer)
    {
        this.reader = reader;
        this.writer = writer;
        controllers = new IController[]
        {
            new BasicController(this),
            new GameController(this),
            new FallbackController(this)
        };
    }

    public void execute()
    {
        boolean terminated = false;
        while(!terminated)
        {
            String request = reader.read();
            if (request.equals("exit"))
                terminated = true;
            for (IController controller: controllers)
            {
                boolean isProcessed = controller.processRequest(request);
                if (isProcessed)
                    break;
            }
        }
    }

    public void sendMessage(String message)
    {
        writer.write(message);
    }
}
