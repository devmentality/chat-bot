package main.TelegramApp;

import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.User;
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
    private ConcurrentNewInMemoryRepo repository;
    private String token;

    public TelegramBotsManager(String token, DefaultBotOptions options, ConcurrentNewInMemoryRepo repository)
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

        Long chatId = update.getMessage().getChatId();
        TelegramBotDecorator chatBot;
        User currentUser;
        try
        {
            currentUser = repository.getUser(chatId);
            chatBot = botsStorage.get(chatId);
        }
        catch (Exception ex)
        {
            currentUser = new User();
            currentUser.id = chatId;
            repository.addUser(currentUser);

            chatBot = new TelegramBotDecorator(repository);
            botsStorage.put(chatId, chatBot);
        }

        ArrayList<String> replies = chatBot.processRequest(currentUser, message);

        repository.updateUser(currentUser);
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