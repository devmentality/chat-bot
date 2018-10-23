package main;

import main.Data.ConcurrentInMemoryRepo;
import main.Resources.Strings;
import java.util.ArrayList;
import java.util.Arrays;

public class TelegramBotDecorator
{
    private Bot chatBot;

    public TelegramBotDecorator(ConcurrentInMemoryRepo repository, Session session)
    {
        chatBot = new Bot(repository, session);
    }

    public ArrayList<String> processRequest(String request)
    {
        request = validateRequest(request);
        System.out.println(request);
        if (request.equals("/start"))
            return new ArrayList<>(Arrays.asList(Strings.introduction));

        return chatBot.processRequest(request);
    }

    private String validateRequest(String request)
    {
        if (request.startsWith("/"))
        {
            String requestWithoutCommandPrefix = request.substring(1);
            String[] splitedRequest = requestWithoutCommandPrefix.split("\\s+");

            ArrayList<String> commands = chatBot.getCurrentState().getAvailableCommands();
            if (commands.contains(splitedRequest[0]))
                return requestWithoutCommandPrefix;
        }
        return request;
    }
}
