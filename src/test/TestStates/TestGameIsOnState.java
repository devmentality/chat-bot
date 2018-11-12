package test.TestStates;

import main.Bot;
import main.Data.ChallengeRepository;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.User;
import main.GameLogic.Attempt;
import main.GameLogic.Game;
import main.GameLogic.GuessResult;
import main.States.GameIsOnState;
import main.States.InitializedState;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestGameIsOnState
{
    private Bot bot;
    private GameIsOnState state;
    private ConcurrentNewInMemoryRepo repository;
    private Game game;
    private User user;
    private int[] guessedDigits = new int[]{1, 2, 3, 4};
    private String victoriousGuess = "1234";

    @Before
    public final void assign()
    {
        game = new Game(guessedDigits);
        repository = new ConcurrentNewInMemoryRepo();
        bot = new Bot(repository, new ChallengeRepository());
        state = new GameIsOnState(bot, repository);
        bot.changeState(state);
        user = new User();
        user.unfinishedGame = game;
    }

    @Test
    public final void testSwitchesStateOnVictory()
    {
        state.processRequest(user, victoriousGuess);

        Assert.assertTrue(bot.getCurrentState() instanceof InitializedState);
    }

    @Test
    public final void testAddsGameResultOnVictory()
    {
        state.processRequest(user, victoriousGuess);

        Assert.assertEquals(1, user.gameResults.size());
        Assert.assertTrue(user.gameResults.get(0).isVictory());
    }

    private void playGame(int numberAttempts)
    {
        int[] wrongGuess = new int[] {1, 2, 3, 5};
        String wrongGuessStr = "1235";

        for(int i = 0; i < numberAttempts; i++)
            game.attempts.add(new Attempt(wrongGuess, new GuessResult(3, 0)));
        
        state.processRequest(user, wrongGuessStr);
    }

    @Test
    public final void testSwitchesStateOnLoss()
    {
        playGame(Game.attemptsToLose - 1);
        Assert.assertTrue(bot.getCurrentState() instanceof InitializedState);
    }
    
    @Test
    public final void testNotSwitchesStateOnLoss()
    {
    	playGame(Game.attemptsToLose - 2);
        Assert.assertTrue(bot.getCurrentState() instanceof GameIsOnState);
    }

    @Test
    public final void testAddsGameResultOnLoss()
    {
        playGame(Game.attemptsToLose);
        Assert.assertEquals(1, user.gameResults.size());
        Assert.assertFalse(user.gameResults.get(0).isVictory());
    }
}
