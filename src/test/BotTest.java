package test;

import main.Bot;
import main.Data.IAppRepository;
import main.Data.InMemoryRepository;
import main.IO.StringBufferReader;
import main.IO.StringBufferWriter;
import main.Resources.Strings;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
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

        ArrayList<String> expectedOutput = new ArrayList<>();
        expectedOutput.add(Strings.startMessage);
        expectedOutput.add(Strings.nameRequest);
        expectedOutput.add(String.format(Strings.greetingNewUser, username));
        expectedOutput.add(Strings.introduction);
        expectedOutput.add(Strings.help);
        expectedOutput.add(Strings.goodbye);

        bot.execute();

        ArrayList<String> output = writer.getBuffer();

        assertEquals(expectedOutput.size(), output.size());
        for (int index = 0; index < expectedOutput.size(); index++)
            assertEquals(expectedOutput.get(index), output.get(index));
    }

    @Test
    public final void testBotStartStopContinueGame() {
        assign(new String[]{"start", username, "newgame", "stop", "continue", "exit"});

        ArrayList<String> expectedOutput = new ArrayList<>();
        expectedOutput.add(Strings.startMessage);
        expectedOutput.add(Strings.nameRequest);
        expectedOutput.add(String.format(Strings.greetingNewUser, username));
        expectedOutput.add(Strings.introduction);
        expectedOutput.add(Strings.newGamePhrase);
        expectedOutput.add(Strings.continueGamePhrase);
        expectedOutput.add(Strings.goodbye);

        bot.execute();

        ArrayList<String> output = writer.getBuffer();

        assertEquals(expectedOutput.size(), output.size());
        for (int index = 0; index < expectedOutput.size(); index++)
            assertEquals(expectedOutput.get(index), output.get(index));
    }
}
