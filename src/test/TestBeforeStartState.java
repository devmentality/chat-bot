package test;

import main.States.BeforeStartState;
import main.Data.InMemoryRepository;
import main.IO.StringBufferWriter;
import main.Resources.Strings;
import org.junit.Test;
import test.mocks.StateMachineMock;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class TestBeforeStartState
{
    @Test
    public final void testValidStart()
    {
        ArrayList<String> output = assignAndAct("start");
        assertEquals(0, output.size());
    }

    @Test
    public final void testInvalidStart()
    {
        ArrayList<String> output = assignAndAct("kek");

        assertEquals(1, output.size());
        assertEquals(Strings.startRequest, output.get(0));
    }

    private ArrayList<String> assignAndAct(String inputCommand)
    {
        StateMachineMock stateMachine = new StateMachineMock();
        StringBufferWriter writer = new StringBufferWriter();
        BeforeStartState state = new BeforeStartState(stateMachine, new InMemoryRepository(), writer);

        state.processRequest(inputCommand);
        return writer.getBuffer();
    }
}
