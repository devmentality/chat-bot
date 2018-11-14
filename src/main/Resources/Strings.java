package main.Resources;

import java.lang.String;

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
    public static final String iDontUnderstand = "I don't understand:(";

    public static final String challengeCreated = "Challenge successfully created";
    public static final String alreadyHaveChallenge = "You already have a challenge";
    public static final String noSuchMode = "There is no such mode";

    public static final String yourChallengePassed = "Your challenge was successfully passed";
    public static final String yourChallengeNotPassed = "Your challenge was not passed";
    public static final String yourChallengeAccepted = "Your challenge was accepted";
    public static final String yourChallengeHasIncorrectNumber = "Incorrect number in challenge";
    public static final String yourChallengeHasIncorrectPoints = "Incorrect points in challenge";


    public static final String challengeSelectorPreamble = "Choose challenge:";
    public static final String challengeDescriptionsPreamble = "Available challenges:";
    public static final String difficutySelectorPreamble = "Choose difficulty";

    public static final String challengeCancelled = "You challenge is cancelled";
    public static final String cantCancelChallenge = "You don't have challenges or your challenge was accepted.";
    public static final String onDeclineChallenges = "Don't be afraid the next time ;)";
    public static final String noAvailableChallenges = "There is no challenges yet";

    public static final String enoughPoints = "You have enough points";
    public static final String gotPoints = "You got %d points";
}
