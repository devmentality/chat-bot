package main.Resources;

public class Strings
{
    public static final String help =
            "   Bot help.\n" +
            "Game bot plays bulls and cows game.\n" +
            "   Commands:\n" +
            "help - shows this message\n" +
            "exit - quit session\n" +
            "newgame - start new game\n" +
            "resign - finish game\n" +
            "stop - stop and save game\n" +
            "continue - continue saved game";

    public static final String startMessage = "Hi, I'm bot. Send 'start' to start dialog";

    public static final String greetingNewUser = "Nice to meet you, %s";
    public static final String greetingOldUser = "Yeah, my old friend, %s";
    public static final String nameRequest = "What is your name?";
    public static final String goodbye = "Goodbye!";

    public static final String startRequest = "Send 'start' to start dialog.";
    public static final String introduction =
            "Now you are ready for playing.\n" +
            "Send 'help' to get to know how to use me.";

    public static final String congratulations = "Congrats, you have won!";
    public static final String losePhrase = "You have lost:(";
    public static final String guessFormatFail = "You have to provide 4 digits";
    public static final String guessResultTemplate = "%d bulls and %d cows";

    public static final String noSavedGames = "You don't have unfinished games";
    public static final String statisticsTemplate = "%d games played %d victories %d losses";

    public static final String newGamePhrase = "Let's start. I've made a number with 4 different digits. You goal is to guess it.";
    public static final String continueGamePhrase = "Go on playing.";

    public static final String noAttempts = "You don't have any attempts.";
}
