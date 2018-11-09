package main.TelegramApp;

import main.Bot;
import main.Data.ChallengeRepository;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.User;
import main.PlainResponse;
import main.Resources.Strings;
import main.Response;

import java.util.ArrayList;

public class TelegramBotDecorator
{
    /*
        Класс, оборачивающий чат бота, добавляя обработку команд, начинающися со слеша.
     */
    private Bot chatBot;

    public TelegramBotDecorator(ConcurrentNewInMemoryRepo repository, ChallengeRepository challengeRepository)
    {
        chatBot = new Bot(repository, challengeRepository);
    }

    public ArrayList<Response> processRequest(User user, String request)
    {
        request = validateRequest(request);
        if (request.equals("/start"))
            return Response.compose(new PlainResponse(user.id, Strings.introduction));

        return chatBot.processRequest(user, request);
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
