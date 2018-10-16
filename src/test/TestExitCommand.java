package test;

import main.Commands.ExitCommand;
import main.Data.InMemoryRepository;
import main.IO.StringBufferWriter;
import main.IStateMachine;
import org.junit.Assert;
import org.junit.Test;
import test.mocks.StateMachineMock;

public class TestExitCommand
{
    @Test
    public final void testTerminatesMachine()
    {
        IStateMachine stateMachine = new StateMachineMock();
        ExitCommand command = new ExitCommand(stateMachine, new InMemoryRepository(), new StringBufferWriter());

        command.execute();

        Assert.assertTrue(stateMachine.isTerminated());
    }
}
