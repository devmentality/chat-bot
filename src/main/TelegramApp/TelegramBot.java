package main;

import main.Data.ConcurrentInMemoryRepo;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class TelegramBot extends TelegramLongPollingBot
{
    private ConcurrentHashMap<Long, TelegramBotDecorator> botsStorage;
    private ConcurrentInMemoryRepo repository;
    private String token;

    public TelegramBot(String token, DefaultBotOptions options, ConcurrentInMemoryRepo repository)
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
            sendMsg(update.getMessage().getChatId().toString(), reply);
    }

    private void sendMsg(String chatId, String s)
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
        return token;
    }
}