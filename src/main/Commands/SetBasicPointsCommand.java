package main.Commands;

import main.Bot;
import main.Data.INewRepository;
import main.Data.User;
import main.PlainResponse;
import main.Resources.Strings;
import main.Response;

import java.util.ArrayList;

public class SetBasicPointsCommand extends CommandBase
{
    public SetBasicPointsCommand(Bot bot, INewRepository repository)
    {
        super(bot, repository, "setbasicpoints");
    }

    @Override
    public ArrayList<Response> execute(User user, String... args)
    {
        int basicPoints = 10;
        if (user.points < basicPoints)
        {
            int addedPoints = basicPoints - user.points;
            user.points += addedPoints;
            repository.updateUser(user);

            return Response.compose(
                    new PlainResponse(user.id, String.format(Strings.gotPoints, addedPoints)));
        }
        else
            return Response.compose(new PlainResponse(user.id, Strings.enoughPoints));
    }
}
