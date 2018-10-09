package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import main.IState;
import main.Session;
import main.Data.InMemoryRepository;
import main.GameLogic.Game;
import main.IO.StringBufferWriter;
import main.Resources.Strings;
import main.States.GameIsOnState;
import main.States.InitializedState;
import test.mocks.StateMachineMock;

public class TestStatistics {
	private StateMachineMock stateMachine;
    private StringBufferWriter writer;
    private IState state;
    private InMemoryRepository repository;
    private String username = "user";
    private Game game;
    private int maxGames = 1000;
    
    private final void winOneGame()
    {
    	game = new Game(new int[] {1,2,3,4});
        state = new GameIsOnState(stateMachine, repository, writer, game, new Session(username));
        state.processRequest("1234");
    }
    
    private final void loseOneGame()
    {
    	game = new Game(new int[] {1,2,3,4});
        state = new GameIsOnState(stateMachine, repository, writer, game, new Session(username));
        for (int i = 0; i < 100; i++)
        	state.processRequest("1235");
    }
    
    private final ArrayList<String> getStatistics()
    {
    	writer = new StringBufferWriter();
    	state = new InitializedState(stateMachine, repository, writer, new Session(username));
    	state.processRequest("stat");
        return writer.getBuffer();
    }
    
    @Test
    public final void testCorrectStatistics()
    {
    	stateMachine = new StateMachineMock();
        writer = new StringBufferWriter();
        repository = new InMemoryRepository();
        repository.addUser(username);
    	Random random = new Random();
    	int winGames = random.nextInt(maxGames);
    	int loseGames = random.nextInt(maxGames);
    	for (int i = 0; i < winGames; i++)
    		winOneGame();
    	for(int i = 0; i < loseGames; i++)
    		loseOneGame();
    	ArrayList<String> output = getStatistics();
        assertEquals(1, output.size());
        assertEquals(String.format(Strings.statisticsTemplate, winGames + loseGames, winGames, loseGames),
        		output.get(0));
    }
}
