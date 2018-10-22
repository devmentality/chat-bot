package test;

import main.Bot;
import main.Data.IAppRepository;
import main.Data.InMemoryRepository;
import main.IO.StringBufferReader;
import main.IO.StringBufferWriter;
import main.Resources.Strings;
import main.Session;
import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;

public class BotTest
{
    private String username = "Nik";
    private IAppRepository repository;
    private Bot bot;

    private void assign()
    {
        repository = new InMemoryRepository();
        bot = new Bot(repository, new Session(username));
    }

    @Test
    public final void testBotBasicDialog() {
        assign();
        String[] inputCommands = new String[]{username, "help", "exit"};
        String[] expectedOutput = new String[]
        {
            Strings.nameRequest,
            String.format(Strings.greetingNewUser, username),
            Strings.introduction,
            Strings.help,
            Strings.goodbye
        };

        ArrayList<String> overallOutput = new ArrayList<>();
        for(String command: inputCommands)
            overallOutput.addAll(bot.processRequest(command));
        Assert.assertArrayEquals(expectedOutput, overallOutput.toArray());
    }

    @Test
    public final void testBotStartStopContinueGame() {
        assign();
        String[] inputCommands = new String[]{username, "newgame 4", "stop", "continue", "exit"};
        String[] expectedOutput = new String[]
        {
            Strings.nameRequest,
            String.format(Strings.greetingNewUser, username),
            Strings.introduction,
            String.format(Strings.newGamePhrase, 4),
            Strings.continueGamePhrase,
            Strings.goodbye
        };

        ArrayList<String> overallOutput = new ArrayList<>();
        for(String command: inputCommands)
            overallOutput.addAll(bot.processRequest(command));
        Assert.assertArrayEquals(expectedOutput, overallOutput.toArray());
    }
}
