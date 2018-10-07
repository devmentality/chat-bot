package test;

import main.Data.InMemoryRepository;
import main.IO.StringBufferWriter;
import main.Resources.Strings;
import main.States.StartedState;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import test.mocks.StateMachineMock;

import java.util.ArrayList;

public class TestStartedState
{
    private StateMachineMock stateMachine;
    private StringBufferWriter writer;
    private StartedState state;
    private InMemoryRepository repository;

    @Before
    public final void assignment()
    {
        stateMachine = new StateMachineMock();
        writer = new StringBufferWriter();
        repository = new InMemoryRepository();
        state = new StartedState(stateMachine,repository, writer);
    }

    @Test
    public final void testActivation()
    {
        state.activate();
        ArrayList<String> output = writer.getBuffer();

        assertEquals(Strings.nameRequest, output.get(0));
    }

    @Test
    public final void testGreetingNewUser()
    {
        String userName = "Lol";
        state.processRequest(userName);

        ArrayList<String> output = writer.getBuffer();

        assertEquals(String.format(Strings.greetingNewUser, userName), output.get(0));
    }

    @Test
    public final void testGreetingOldUser()
    {
        String userName = "Lol";
        repository.addUser(userName);

        state.processRequest(userName);

        ArrayList<String> output = writer.getBuffer();
        assertEquals(String.format(Strings.greetingOldUser, userName), output.get(0));
    }
}