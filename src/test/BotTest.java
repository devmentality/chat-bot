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

public class BotTest
{
    @Test
    public final void testBotBasicDialog()
    {
        String username = "Nik";
        StringBufferReader reader = new StringBufferReader(new String[]{"start", username, "help", "exit"});
        StringBufferWriter writer = new StringBufferWriter();
        IAppRepository repository = new InMemoryRepository();

        Bot bot = new Bot(reader, writer, repository);

        bot.execute();

        ArrayList<String> output = writer.getBuffer();

        assertEquals(6, output.size());
        assertEquals(Strings.startMessage, output.get(0));
        assertEquals(Strings.nameRequest, output.get(1));
        assertEquals(String.format(Strings.greetingNewUser, username), output.get(2));
        assertEquals(Strings.introduction, output.get(3));
        assertEquals(Strings.help, output.get(4));
        assertEquals(Strings.goodbye, output.get(5));
    }
}
