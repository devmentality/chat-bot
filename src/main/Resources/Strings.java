package main.Resources;

public class Strings
{
    public static final String help =
        "   Bot help.\n" +
        "Game bot plays bulls and cows game.\n" +
        "   Commands:\n" +
        "start - start playing with bot\n" +
        "newgame - [argument N] start new game with 4 [1 <= N <= 10] digits to guess\n" +
        "stat - get statistics\n" +
        "continue - continue stopped game\n" +
        "   During game:\n" +
        "stop - stop playing (only during playing)\n" +
        "attempts - get all attempts in current game (only during playing)\n" +
        "resign - admit loss (only during playing)\n";


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
    public static final String guessFormatFail = "You have to provide %d digits";
    public static final String guessResultTemplate = "%d bulls and %d cows";

    public static final String noSavedGames = "You don't have unfinished games";
    public static final String statisticsTemplate = "%d games played %d victories %d losses";

    public static final String newGamePhrase = "Let's start. I've made a number with %d different digits. You goal is to guess it.";
    public static final String continueGamePhrase = "Go on playing!";

    public static final String noAttempts = "You don't have any attempts.";
    public static final String onGameStop = "Game is stopped. Use 'continue' to go on playing";
    public static final String onGameResign = "Don't worry! Try another game:)";
}
