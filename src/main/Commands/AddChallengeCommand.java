package main.Commands;

import main.Bot;
import main.Data.Challenge;
import main.Data.ChallengeRepository;
import main.Data.INewRepository;
import main.Data.User;
import main.GameLogic.GameController;
import main.PlainResponse;
import main.Resources.Strings;
import main.Response;
import java.util.ArrayList;


public class AddChallengeCommand extends CommandBase
{
    private ChallengeRepository challengeRepository;

    public AddChallengeCommand(Bot bot, INewRepository repository,
                               ChallengeRepository challengeRepository)
    {
        super(bot, repository, "addchallenge");
        this.challengeRepository = challengeRepository;
    }

    @Override
    public int getAmountOfArgs()
    {
        return 2;
    }

    @Override
    public ArrayList<Response> execute(User user, String... args)
    {
        int points;
        try
        {
            points = Integer.parseInt(args[1]);
            if (user.points < points || points < 0)
                throw new NumberFormatException();
        }
        catch(NumberFormatException ex)
        {
            return Response.compose(new PlainResponse(user.id, Strings.yourChallengeHasIncorrectPoints));
        }

        int[] digits;
        try
        {
            digits = GameController.parseGuess(args[0]);
            if (!GameController.checkUniqueDigits(digits) || args[0].length() != 4)
                throw new NumberFormatException();
        }
        catch (NumberFormatException ex)
        {
            return Response.compose(new PlainResponse(user.id, Strings.yourChallengeHasIncorrectNumber));
        }

        try
        {
            challengeRepository.addChallenge(user.id, new Challenge(user.id, digits, points));
            user.points -= points;
            repository.updateUser(user);

            return Response.compose(new PlainResponse(user.id, Strings.challengeCreated));
        }
        catch (IllegalArgumentException ex)
        {
            return Response.compose(new PlainResponse(user.id, Strings.alreadyHaveChallenge));
        }
    }
}
