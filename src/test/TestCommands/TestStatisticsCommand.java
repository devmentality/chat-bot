package test.TestCommands;

import main.Commands.StatisticsCommand;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.InMemoryRepository;
import main.Data.User;
import main.IO.StringBufferWriter;
import main.Resources.Strings;
import org.junit.Test;
import test.mocks.StateMachineMock;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestStatisticsCommand
{
    @Test
    public final void testCorrectStatisticsFormat()
    {
        StateMachineMock stateMachine = new StateMachineMock();
        StringBufferWriter writer = new StringBufferWriter();
        ConcurrentNewInMemoryRepo repository = new ConcurrentNewInMemoryRepo();
        User user = new User();
        StatisticsCommand command = new StatisticsCommand(stateMachine, repository);

        ArrayList<String> output = command.execute(user).get(0).getContent();

        assertEquals(1, output.size());
        assertEquals(String.format(Strings.statisticsTemplate, 0, 0, 0), output.get(0));
    }
}
