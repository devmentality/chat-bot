package test.BotTest;

import main.Bot;
import main.Data.INewRepository;


public class BotTest
{
    private String username = "Nik";
    private INewRepository repository;
    private Bot bot;
    /*
    private void assign()
    {
        repository = new ConcurrentNewInMemoryRepo();
        bot = new Bot(repository);
    }

    @Test
    public final void testBotBasicDialog() {
        assign();
        String[] inputCommands = new String[]{"help"};
        String[] expectedOutput = new String[]
        {
            Strings.help,
        };

        ArrayList<String> overallOutput = new ArrayList<>();
        for(String command: inputCommands)
            overallOutput.addAll(bot.processRequest(command));
        Assert.assertArrayEquals(expectedOutput, overallOutput.toArray());
    }

    @Test
    public final void testBotStartStopContinueGame() {
        assign();
        String[] inputCommands = new String[]{"newgame 4", "stop", "continue"};
        String[] expectedOutput = new String[]
        {
            String.format(Strings.newGamePhrase, 4),
            Strings.onGameStop,
            Strings.continueGamePhrase
        };

        ArrayList<String> overallOutput = new ArrayList<>();
        for(String command: inputCommands)
            overallOutput.addAll(bot.processRequest(command));
        Assert.assertArrayEquals(expectedOutput, overallOutput.toArray());
    }

    @Test
    public final void testTwoUsersOnStopGame()
    {
        InMemoryRepository repository = new InMemoryRepository();
        String username1 = "user1";
        String username2 = "user2";
        repository.addUser(username1);
        repository.addUser(username2);

        Bot firstBot = new Bot(repository, new Session(username1));
        Bot secondBot = new Bot(repository, new Session(username2));

        firstBot.processRequest("newgame");
        firstBot.processRequest("stop");

        Assert.assertTrue(repository.hasUnfinishedGame(username1));
        Assert.assertFalse(repository.hasUnfinishedGame(username2));
    }

    @Test
    public final void testTwoUsersOnFinishGame()
    {
        InMemoryRepository repository = new InMemoryRepository();
        String username1 = "user1";
        String username2 = "user2";
        repository.addUser(username1);
        repository.addUser(username2);

        Bot firstBot = new Bot(repository);
        Bot secondBot = new Bot(repository);

        firstBot.processRequest("newgame");
        firstBot.processRequest("resign");

        Assert.assertFalse(repository.getGameResults(username1).isEmpty());
        Assert.assertTrue(repository.getGameResults(username2).isEmpty());
    }
    */
}
