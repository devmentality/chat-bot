package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.Test;

import main.IState;
import main.Session;
import main.Data.InMemoryRepository;
import main.GameLogic.Game;
import main.IO.StringBufferWriter;
import main.Resources.Strings;
import main.States.GameIsOnState;
import test.mocks.StateMachineMock;

public class TestLosing {
    private int maxAttempts = 100;
    
    @Test
    public final void TestCorrectLosing()
    {
        StateMachineMock stateMachine = new StateMachineMock();
        StringBufferWriter writer = new StringBufferWriter();
        InMemoryRepository repository = new InMemoryRepository();
        String username = "user";
        repository.addUser(username);
        writer = new StringBufferWriter();
    	Game game = new Game(new int[] {1,2,3,4});
        IState state = new GameIsOnState(stateMachine, repository, writer, game, new Session(username));
        for(int i = 0; i < maxAttempts - 1; i++)
        {
        	state.processRequest("1235");
        	ArrayList<String> output = writer.getBuffer();
        	assertNotEquals(output.get(output.size() - 1), Strings.losePhrase);
        }
    	state.processRequest("1235");
    	ArrayList<String> output = writer.getBuffer();
    	assertEquals(output.get(output.size() - 1), Strings.losePhrase);
    }
}
