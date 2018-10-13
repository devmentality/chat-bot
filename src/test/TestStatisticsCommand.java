package test;

import main.Commands.StatisticsCommand;
import main.Data.InMemoryRepository;
import main.GameLogic.Game;
import main.GameLogic.GameResult;
import main.IO.StringBufferWriter;
import main.IState;
import main.Resources.Strings;
import main.Session;
import main.States.GameIsOnState;
import main.States.InitializedState;
import org.junit.Test;
import test.mocks.StateMachineMock;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestStatisticsCommand
{
    @Test
    public final void testCorrectStatistics()
    {
        String username = "name";
        StateMachineMock stateMachine = new StateMachineMock();
        StringBufferWriter writer = new StringBufferWriter();
        InMemoryRepository repository = new InMemoryRepository();
        repository.addUser(username);
        StatisticsCommand command = new StatisticsCommand(
                stateMachine, repository, writer, new Session(username));

        command.execute();

        ArrayList<String> output = writer.getBuffer();
        assertEquals(1, output.size());
        assertEquals(String.format(Strings.statisticsTemplate, 0, 0, 0), output.get(0));
    }
}
