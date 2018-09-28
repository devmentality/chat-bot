package main;

import main.Data.IAppRepository;

public class Bot
{
    private IMessageReader reader;
    private IMessageWriter writer;
    private IController[] controllers;
    private IAppRepository repository;
    private String userName;

    public Bot(IMessageReader reader, IMessageWriter writer, IAppRepository repository)
    {
        this.reader = reader;
        this.writer = writer;
        this.repository = repository;

        controllers = new IController[]
        {
            new BasicController(this, repository),
            new GameController(this, repository),
            new FallbackController(this)
        };
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
        boolean terminated = false;
        writer.write(getWelcoming());
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

    private String getWelcoming()
    {
        return "Hi, I'm bot. Send 'start' to start dialog";
    }
}
