package test;

import main.Data.InMemoryRepository;
import main.GameLogic.Attempt;
import main.GameLogic.Game;
import main.GameLogic.GuessResult;
import main.IO.StringBufferWriter;
import main.Session;
import main.States.GameIsOnState;
import main.States.InitializedState;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.mocks.StateMachineMock;

public class TestGameIsOnState
{
    private StateMachineMock stateMachine;
    private GameIsOnState state;
    private InMemoryRepository repository;
    private Game game;
    
    private String username = "user";
    private int[] guessedDigits = new int[]{1, 2, 3, 4};
    private String victoriousGuess = "1234";

    @Before
    public final void assign()
    {
        game = new Game(guessedDigits);
        repository = new InMemoryRepository();
        repository.addUser(username);
        stateMachine = new StateMachineMock();
        stateMachine.changeState(new GameIsOnState(stateMachine, repository, game, new Session(username)));
        state = new GameIsOnState(stateMachine, repository, game, new Session(username));
    }

    @Test
    public final void testSwitchesStateOnVictory()
    {
        state.processRequest(victoriousGuess);

        Assert.assertTrue(stateMachine.getCurrentState() instanceof InitializedState);
    }

    @Test
    public final void testAddsGameResultOnVictory()
    {
        state.processRequest(victoriousGuess);

        Assert.assertEquals(1, repository.getGameResults(username).size());
        Assert.assertTrue(repository.getGameResults(username).get(0).isVictory());
    }

    private void playGame(int numberAttempts)
    {
        int[] wrongGuess = new int[] {1, 2, 3, 5};
        String wrongGuessStr = "1235";

        for(int i = 0; i < numberAttempts; i++)
            game.attempts.add(new Attempt(wrongGuess, new GuessResult(3, 0)));
        
        state.processRequest(wrongGuessStr);
    }

    @Test
    public final void testSwitchesStateOnLoss()
    {
        playGame(Game.attemptsToLose - 1);
        Assert.assertTrue(stateMachine.getCurrentState() instanceof InitializedState);
    }
    
    @Test
    public final void testNotSwitchesStateOnLoss()
    {
    	playGame(Game.attemptsToLose - 2);
        Assert.assertTrue(stateMachine.getCurrentState() instanceof GameIsOnState);
    }

    @Test
    public final void testAddsGameResultOnLoss()
    {
        playGame(Game.attemptsToLose);
        Assert.assertEquals(1, repository.getGameResults(username).size());
        Assert.assertFalse(repository.getGameResults(username).get(0).isVictory());
    }
}
