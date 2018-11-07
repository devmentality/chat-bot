package test.TestCommands;

import main.Bot;
import main.Commands.StatisticsCommand;
import main.Data.ChallengeRepository;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.InMemoryRepository;
import main.Data.User;
import main.IO.StringBufferWriter;
import main.PlainResponse;
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
        ConcurrentNewInMemoryRepo repository = new ConcurrentNewInMemoryRepo();
        ChallengeRepository challengeRepository = new ChallengeRepository();

        Bot bot = new Bot(repository, challengeRepository);
        User user = new User();
        StatisticsCommand command = new StatisticsCommand(bot, repository);

        ArrayList<String> output = ((PlainResponse)command.execute(user).get(0)).getContent();

        assertEquals(1, output.size());
        assertEquals(String.format(Strings.statisticsTemplate, 0, 0, 0), output.get(0));
    }
}
