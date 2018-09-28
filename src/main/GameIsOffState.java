package main;

import main.Data.IAppRepository;

import java.util.ArrayList;

public class GameIsOffState extends ControllerStateBase
{
    public GameIsOffState(IController controller, Bot bot, IAppRepository repository)
    {
        super(controller, bot, repository);
    }

    @Override
    public boolean processRequest(String request) {
        if (request.equals("play"))
        {
            bot.sendMessage("let's play");
            controller.changeState(new GameIsOnState(controller, bot, repository));
        }
        else if (request.equals("stat"))
        {
            ArrayList<GameResult> results = repository.getGameResults(bot.getUserName());
            int victories = 0;
            int losses = 0;

            for (GameResult gameResult: results)
                if (gameResult.isVictory())
                    victories++;
                else
                    losses++;

            bot.sendMessage(String.format("%d games played: %d victories and %d losses",
                    results.size(), victories, losses));
        }
        else
            return false;
        return true;
    }
}
