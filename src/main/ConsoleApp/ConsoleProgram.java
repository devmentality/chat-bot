package main;

import main.Data.InMemoryRepository;
import main.Resources.Strings;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleProgram
{
    private static void printReplies(ArrayList<String> replies)
    {
        for(String reply: replies)
            System.out.println(reply);
    }

    public static void main(String[] args)
    {
        InMemoryRepository repository = new InMemoryRepository();
        Scanner sc = new Scanner(System.in);

        while (true)
        {
            System.out.println(Strings.nameRequest);
            String username = sc.nextLine();

            Bot chatBot = new Bot(repository, new Session(username));

            while (!chatBot.isTerminated())
            {
                String message = sc.nextLine();
                ArrayList<String> replies = chatBot.processRequest(message);
                for(String reply: replies)
                    System.out.println(reply);
            }
        }
    }
}
