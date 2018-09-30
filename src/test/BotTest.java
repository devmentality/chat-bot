package test;

import main.Bot;
import main.Data.IAppRepository;
import main.Data.InMemoryRepository;
import main.IO.StringBufferReader;
import main.IO.StringBufferWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;



public class BotTest
{
    @Test
    public final void testBotSetupAndExit()
    {
        StringBufferReader reader = new StringBufferReader(new String[]{"exit"});
        StringBufferWriter writer = new StringBufferWriter();
        IAppRepository repository = new InMemoryRepository();

        Bot bot = new Bot(reader, writer, repository);

        bot.execute();

        ArrayList<String> output = writer.getBuffer();

        assertEquals(1, output.size());
        assertEquals("Send 'start' to start dialog", output.get(0));
    }
}
