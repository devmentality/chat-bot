package main.TelegramApp;

import main.Data.ConcurrentInMemoryRepo;
import main.Session;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class TelegramBotsManager extends TelegramLongPollingBot
{
    private ConcurrentHashMap<Long, TelegramBotDecorator> botsStorage;
    private ConcurrentInMemoryRepo repository;
    private String token;

    public TelegramBotsManager(String token, DefaultBotOptions options, ConcurrentInMemoryRepo repository)
    {
        super(options);
        botsStorage = new ConcurrentHashMap<>();
        this.repository = repository;
        this.token = token;
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        String message = update.getMessage().getText();
        System.out.println("LOG: " + message);

        TelegramBotDecorator chatBot;
        Long chatId = update.getMessage().getChatId();
        if (botsStorage.containsKey(chatId))
            chatBot = botsStorage.get(chatId);
        else
        {
            chatBot = new TelegramBotDecorator(repository, new Session(chatId.toString()));
            botsStorage.put(update.getMessage().getChatId(), chatBot);
        }

        ArrayList<String> replies = chatBot.processRequest(message);

        for(String reply: replies)
            sendMessage(update.getMessage().getChatId().toString(), reply);
    }

    private void sendMessage(String chatId, String s)
    {
        SendMessage messageToSend = new SendMessage();
        messageToSend.enableMarkdown(true);
        messageToSend.setChatId(chatId);
        messageToSend.setText(s);
        try
        {
            execute(messageToSend);
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
        return token;
    }
}