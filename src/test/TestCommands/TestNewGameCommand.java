package test.TestCommands;

import main.Commands.NewGameCommand;
import main.Data.InMemoryRepository;
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
        NewGameCommand command = new NewGameCommand(stateMachine, new InMemoryRepository(),
                new Session("user"));

        command.execute("4");
        Assert.assertTrue(stateMachine.getCurrentState() instanceof GameIsOnState);
    }

    @Test(expected = NumberFormatException.class)
    public final void testIncorrectArgument()
    {
        IStateMachine stateMachine = new StateMachineMock();
        NewGameCommand command = new NewGameCommand(stateMachine, new InMemoryRepository(),
                new Session("user"));

        command.execute("a");
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testArgumentGraterThanMax()
    {
        IStateMachine stateMachine = new StateMachineMock();
        NewGameCommand command = new NewGameCommand(stateMachine, new InMemoryRepository(),
                new Session("user"));

        command.execute("11");
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testArgumentLessThanMin()
    {
        IStateMachine stateMachine = new StateMachineMock();
        NewGameCommand command = new NewGameCommand(stateMachine, new InMemoryRepository(),
                new Session("user"));

        command.execute("-1");
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testEmptyArgument()
    {
        IStateMachine stateMachine = new StateMachineMock();
        NewGameCommand command = new NewGameCommand(stateMachine, new InMemoryRepository(),
                new Session("user"));

        command.execute(" ");
    }
}
