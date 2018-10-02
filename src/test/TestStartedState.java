package test;

import main.Data.InMemoryRepository;
import main.IO.StringBufferWriter;
import main.Resources.Strings;
import main.StartedState;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import test.mocks.StateMachineMock;

import java.util.ArrayList;

public class TestStartedState
{
    @Test
    public final void testActivation()
    {
        StateMachineMock stateMachine = new StateMachineMock();
        StringBufferWriter writer = new StringBufferWriter();
        StartedState state = new StartedState(stateMachine, new InMemoryRepository(), writer);

        state.activate();
        ArrayList<String> output = writer.getBuffer();

        assertEquals(Strings.nameRequest, output.get(0));
    }

    @Test
    public final void testGreetingNewUser()
    {
        StateMachineMock stateMachine = new StateMachineMock();
        StringBufferWriter writer = new StringBufferWriter();
        StartedState state = new StartedState(stateMachine, new InMemoryRepository(), writer);

        String userName = "Lol";
        state.processRequest(userName);

        ArrayList<String> output = writer.getBuffer();

        assertEquals(String.format(Strings.greetingNewUser, userName), output.get(0));
    }

    @Test
    public final void testGreetingOldUser()
    {
        String userName = "Lol";

        StateMachineMock stateMachine = new StateMachineMock();
        StringBufferWriter writer = new StringBufferWriter();
        InMemoryRepository repository = new InMemoryRepository();
        repository.addUser(userName);
        StartedState state = new StartedState(stateMachine, repository, writer);

        state.processRequest(userName);

        ArrayList<String> output = writer.getBuffer();
        assertEquals(String.format(Strings.greetingOldUser, userName), output.get(0));
    }
}
