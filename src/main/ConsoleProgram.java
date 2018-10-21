package main;

import main.Data.InMemoryRepository;
import main.IO.ConsoleWriter;
import main.Resources.Strings;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleProgram
{
    public static void main(String[] args)
    {
        InMemoryRepository repository = new InMemoryRepository();
        Scanner sc = new Scanner(System.in);

        while (true)
        {
            System.out.println(Strings.startRequest);
            Bot chatBot = new Bot(repository);

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
