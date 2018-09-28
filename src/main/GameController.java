package main;

import main.Data.IAppRepository;

public class GameController implements IController
{
    private Bot bot;
    private IControllerState state;
    private Game currentGame;
    private IAppRepository repository;

    public GameController(Bot bot, IAppRepository repository)
    {
        this.bot = bot;
        this.repository = repository;
        state = new GameIsOffState(this, bot, repository);
    }

    public boolean processRequest(String request)
    {
        return state.processRequest(request);
    }

    public void changeState(IControllerState newState)
    {
        state = newState;
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
