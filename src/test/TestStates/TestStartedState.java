package test.TestStates;

import main.Data.InMemoryRepository;
import main.IO.StringBufferWriter;
import main.Resources.Strings;
import main.States.InitializedState;
import main.States.StartedState;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.mocks.StateMachineMock;

import java.util.ArrayList;

public class TestStartedState
{
    private StateMachineMock stateMachine;
    private StringBufferWriter writer;
    private StartedState state;
    private InMemoryRepository repository;

    @Before
    public final void assign()
    {
        stateMachine = new StateMachineMock();
        writer = new StringBufferWriter();
        repository = new InMemoryRepository();
        state = new StartedState(stateMachine,repository);
    }

    @Test
    public final void testGreetingNewUser()
    {
        String userName = "Lol";
        ArrayList<String> output = state.processRequest(userName);
        Assert.assertEquals(String.format(Strings.greetingNewUser, userName), output.get(0));
    }

    @Test
    public final void testGreetingOldUser()
    {
        String userName = "Lol";
        repository.addUser(userName);

        ArrayList<String> output = state.processRequest(userName);
        Assert.assertEquals(String.format(Strings.greetingOldUser, userName), output.get(0));
    }

    @Test
    public final void testSwitchesState()
    {
        state.processRequest("somebody");

        Assert.assertTrue(stateMachine.getCurrentState() instanceof InitializedState);
    }
}