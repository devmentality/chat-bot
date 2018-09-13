package test;

import main.Bot;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class BotTest
{
    @Test
    public final void testGetGreeting()
    {
        Bot bot = new Bot();
        assertEquals("Hello, John", bot.getGreeting("John"));
    }
}
