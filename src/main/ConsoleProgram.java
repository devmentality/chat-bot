package main;

import main.Data.InMemoryRepository;
import main.IO.ConsoleWriter;
import main.Resources.Strings;

import java.util.Scanner;

public class ConsoleProgram
{
    public static void main(String[] args)
    {
        ConsoleWriter writer = new ConsoleWriter();
        InMemoryRepository repository = new InMemoryRepository();
        Scanner sc = new Scanner(System.in);

        while (true)
        {
            System.out.println(Strings.startRequest);
            Bot chatBot = new Bot(writer, repository);

            while (!chatBot.isTerminated())
            {
                String message = sc.nextLine();
                chatBot.processRequest(message);
            }
        }
    }
}
