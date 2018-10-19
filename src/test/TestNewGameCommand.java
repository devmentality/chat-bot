package test;

import main.Commands.NewGameCommand;
import main.Data.InMemoryRepository;
import main.IO.StringBufferWriter;
import main.IStateMachine;
import main.Session;
import main.States.GameIsOnState;
import org.junit.Test;
import org.junit.Assert;
import test.mocks.StateMachineMock;

public class TestNewGameCommand
{
    @Test
    public final void testSwitchesState()
    {
        IStateMachine stateMachine = new StateMachineMock();
        NewGameCommand command = new NewGameCommand(stateMachine,
                new InMemoryRepository(), new StringBufferWriter(), new Session("user"));

        command.execute("4");
        Assert.assertTrue(stateMachine.getCurrentState() instanceof GameIsOnState);
    }
}
