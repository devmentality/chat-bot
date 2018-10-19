package test;

import main.Bot;
import main.Data.IAppRepository;
import main.Data.InMemoryRepository;
import main.IO.StringBufferReader;
import main.IO.StringBufferWriter;
import main.Resources.Strings;
import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;

public class BotTest {
    private String username = "Nik";
    private StringBufferReader reader;
    private StringBufferWriter writer;
    private IAppRepository repository;
    private Bot bot;

    private void assign(String[] input) {
        reader = new StringBufferReader(input);
        writer = new StringBufferWriter();
        repository = new InMemoryRepository();
        bot = new Bot(reader, writer, repository);
    }

    @Test
    public final void testBotBasicDialog() {
        assign(new String[]{"start", username, "help", "exit"});

        String[] expectedOutput = new String[]
        {
            Strings.startMessage,
            Strings.nameRequest,
            String.format(Strings.greetingNewUser, username),
            Strings.introduction,
            Strings.help,
            Strings.goodbye
        };

        bot.execute();
        ArrayList<String> output = writer.getBuffer();
        Assert.assertArrayEquals(expectedOutput, output.toArray());
    }

    @Test
    public final void testBotStartStopContinueGame() {
        assign(new String[]{"start", username, "newgame 4", "stop", "continue", "exit"});

        String[] expectedOutput = new String[]
        {
            Strings.startMessage,
            Strings.nameRequest,
            String.format(Strings.greetingNewUser, username),
            Strings.introduction,
            String.format(Strings.newGamePhrase, 4),
            Strings.continueGamePhrase,
            Strings.goodbye
        };

        bot.execute();
        ArrayList<String> output = writer.getBuffer();
        Assert.assertArrayEquals(expectedOutput, output.toArray());
    }
}
