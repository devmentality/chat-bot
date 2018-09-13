package main;

import java.util.Random;
import java.util.Scanner;

public class Bot
{
    public static void main(String [] args)
    {
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        System.out.println(getGreeting(name));

        boolean closed = false;
        while(!closed)
        {
            System.out.print("I'm listening: ");
            String action = in.nextLine();

            closed = performAction(action);
        }
    }

    public static boolean performAction(String action)
    {
        /*
            Функция обработки команды
            Возвращает флаг, завершена ли работа с ботом
         */
        switch (action)
        {
            case "help":
            {
                System.out.println(getHelp());
                break;
            }
            case "play":
            {
                Question question = generateQuestion();
                System.out.println(question.getQuestion() + "=?");

                Scanner in = new Scanner(System.in);
                String answer = in.nextLine();

                if (question.getAnswer().equals(answer))
                    System.out.println("You are beyond human level, excellent!");
                else
                    System.out.println("Humans often make mistakes...");
                break;
            }
            case "exit":
            {
                System.out.println("I will miss you, human");
                return true;
            }
            default:
            {
                System.out.println("Sorry, I did not understand.");
                System.out.println(getHelp());
            }
        }
        return false;
    }

    public static String getHelp()
    {
        String helpMessage = "I'm chat-bot. I'm quite stupid for now:D\n" +
                             "I understand only these commands:\n" +
                             "1. help - to see this message\n" +
                             "2. play - to answer my easy question\n" +
                             "3. exit - to say goodbye to me";

        return helpMessage;
    }

    public static String getGreeting(String name)
    {
        return String.format("Hello, %s", name);
    }

    public static Question generateQuestion()
    {
        Random rnd = new Random();
        int firstOperand = rnd.nextInt(1000);
        int secondOperand = rnd.nextInt(1000);

        int answer = firstOperand + secondOperand;

        return new Question(String.format("%d + %d", firstOperand, secondOperand), Integer.toString(answer));
    }
}
