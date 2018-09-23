package test;

import main.Bot;
import main.StringBufferReader;
import main.StringBufferWriter;
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
        Bot bot = new Bot(reader, writer);

        bot.execute();

        ArrayList<String> output = writer.getBuffer();

        assertEquals(1, output.size());
        assertEquals("Goodbye!", output.get(0));
    }
}
