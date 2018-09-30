package main;

import main.Data.IAppRepository;
import main.IO.IMessageWriter;

public class GameIsOnState extends StateBase
{
    private Game game;

    public GameIsOnState(IStateMachine stateMachine, IAppRepository repository, IMessageWriter writer)
    {
        super(stateMachine, repository, writer);
        game = new Game();
    }

    @Override
    public void processRequest(String request) {
        if (request.equals("play"))
            doPlay();
        else if (request.equals("resign"))
            doResign();
        else
            doGameAttempt(request);
    }

    private void doPlay()
    {
        writer.write("You are already playing");
    }

    private void doResign()
    {
        writer.write(":(");
        //stateMachine.changeState(new GameIsOffState(controller, bot, repository));
        //repository.addGameResult(bot.getUserName(), new GameResult(false));
    }

    private void doGameAttempt(String request)
    {
        /*
        try
        {
            int[] guessedDigits = parseGuess(request);
            GuessResult result = game.respondOnGuess(guessedDigits);
            bot.sendMessage(String.format("%d bulls and %d cows", result.amountOfBulls, result.amountOfCows));
            if (result.amountOfBulls == 4)
            {
                bot.sendMessage("Congrats, you have won!");
                controller.changeState(new GameIsOffState(controller, bot, repository));
                repository.addGameResult(bot.getUserName(), new GameResult(true));
            }
        }
        catch (Exception ex)
        {
            bot.sendMessage("You have to provide 4 digits");
        }*/
    }
    /*
    private int[] parseGuess(String guess)
    {
        int[] digits = new int[guess.length()];
        for(int index = 0; index < guess.length(); index++)
            digits[index] = Integer.parseInt(String.valueOf(guess.charAt(index)));
        return digits;
    }
    */
}
