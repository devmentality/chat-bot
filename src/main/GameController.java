package main;

public class GameController implements IController
{
    private Bot bot;
    private Game currentGame;

    public GameController(Bot bot)
    {
        this.bot = bot;
    }

    public boolean processRequest(String request)
    {
        if (request.equals("play"))
        {
            if (currentGame == null)
            {
                bot.sendMessage("Let's play.");
                initializeGame();
                bot.sendMessage("I've made a guess.");
            }
            else
            {
                bot.sendMessage("You are already playing. Send resign to finish the game");
            }
        }
        else if (request.equals("resign"))
        {
            bot.sendMessage("No problem");
            currentGame = null;
        }
        else
            try
            {
                int[] guess = parseGuess(request);
                GuessResult result = currentGame.respondOnGuess(guess);
                bot.sendMessage(String.format("%d bulls and %d cows", result.amountOfBulls, result.amountOfCows));
            }
            catch (Exception exception)
            {
                return false;
            }
            return true;
    }

    public static int[] parseGuess(String guess)
    {
        int[] digits = new int[guess.length()];
        for(int index = 0; index < guess.length(); index++)
            digits[index] = Integer.parseInt(String.valueOf(guess.charAt(index)));
        return digits;
    }

    private void initializeGame()
    {
        currentGame = new Game();
    }
}
