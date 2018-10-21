package main;

import main.IO.StringBufferWriter;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

public class TelegramBot extends TelegramLongPollingBot
{
    private Bot chatBot;

    public TelegramBot(DefaultBotOptions options, Bot chatBot)
    {
        super(options);
        this.chatBot = chatBot;
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        String message = update.getMessage().getText();
        System.out.println("LOG: " + message);
        ArrayList<String> replies = chatBot.processRequest(message);

        for(String reply: replies)
            sendMsg(update.getMessage().getChatId().toString(), reply);
        replies.clear();
    }

    private synchronized void sendMsg(String chatId, String s)
    {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try
        {
            execute(sendMessage);
        }
        catch (TelegramApiException e)
        {
            System.out.println("Exception: " + e.toString());
        }
    }

    @Override
    public String getBotUsername()
    {
        return "bulls_and_cows_test997_bot";
    }

    @Override
    public String getBotToken()
    {
        return "687525211:AAFE8KvaUjjN4krtijHKKnDw8683EEWIhWY";
    }
}