package main.Commands;

import main.Bot;
import main.Data.Challenge;
import main.Data.ChallengeRepository;
import main.Data.INewRepository;
import main.Data.User;
import main.GameLogic.Game;
import main.IStateMachine;
import main.PlainResponse;
import main.Resources.Strings;
import main.Response;

import java.util.ArrayList;
import java.util.HashSet;

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

    public boolean checkUniqueDigits(int number)
    {
        if (number < 0)
            return false;
        HashSet<Integer> digits = new HashSet<>();
        while(number != 0)
        {
            digits.add(number % 10);
            number /= 10;
        }
        return digits.size() == 4;
    }


    @Override
    public ArrayList<Response> execute(User user, String... args)
    {
        if (!challengeRepository.hasChallenge(user.id))
        {
            try
            {
                int number = Integer.parseInt(args[0]);
                int points = Integer.parseInt(args[1]);
                if (user.points < points)
                    throw new Exception("Not enough points");
                if (checkUniqueDigits(number))
                    challengeRepository.addChallenge(user.id, number, points);
                user.points -= points;
                repository.updateUser(user);
            }
            catch(Exception e)
            {
                return Response.compose(new PlainResponse(user.id, Strings.yourChallengeIncorrect));
            }

            return Response.compose(new PlainResponse(user.id, Strings.challengeCreated));
        }
        else
            return Response.compose(new PlainResponse(user.id, Strings.alreadyHaveChallenge));
    }
}
