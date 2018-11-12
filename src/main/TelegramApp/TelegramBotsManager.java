package main.TelegramApp;

import main.Data.ChallengeRepository;
import main.Data.ConcurrentNewInMemoryRepo;
import main.Data.User;
import main.PlainResponse;
import main.Response;
import main.SelectorResponse;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class TelegramBotsManager extends TelegramLongPollingBot
{
    private ConcurrentHashMap<Long, TelegramBotDecorator> botsStorage;
    private ConcurrentHashMap<Long, Boolean> removeKeyboardFor;
    private ConcurrentNewInMemoryRepo repository;
    private ChallengeRepository challengeRepository;
    private String token;

    public TelegramBotsManager(String token, DefaultBotOptions options,
                               ConcurrentNewInMemoryRepo repository, ChallengeRepository challengeRepository)
    {
        super(options);
        botsStorage = new ConcurrentHashMap<>();
        removeKeyboardFor = new ConcurrentHashMap<>();
        this.repository = repository;
        this.challengeRepository = challengeRepository;
        this.token = token;
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        String message = update.getMessage().getText();
        System.out.println("LOG: " + message);
        Long chatId = update.getMessage().getChatId();
        TelegramBotDecorator currentChatBot;
        User currentUser;
        try
        {
            currentUser = repository.getUser(chatId);
            currentChatBot = botsStorage.get(chatId);
        }
        catch (Exception ex)
        {
            currentUser = new User();
            currentUser.id = chatId;
            currentUser.username = update.getMessage().getFrom().getFirstName();
            System.out.println(currentUser.username);
            repository.addUser(currentUser);
            currentChatBot = new TelegramBotDecorator(repository, challengeRepository);
            botsStorage.put(chatId, currentChatBot);
        }

        ArrayList<Response> responses = currentChatBot.processRequest(currentUser, message);

        for(Response response: responses)
            handleResponse(response);
    }

    private void handleResponse(Response response)
    {
        if (response instanceof PlainResponse)
            for (String message: ((PlainResponse)response).getContent())
                sendPlainMessage(response.getReceiverId(), message);
        else if (response instanceof SelectorResponse)
            sendSelectorMessage((SelectorResponse)response);
    }

    private void sendPlainMessage(long chatId, String message)
    {
        SendMessage messageToSend = new SendMessage();
        messageToSend.enableMarkdown(true);
        messageToSend.setChatId(chatId);
        messageToSend.setText(message);

        Boolean shouldRemoveKeyboard = removeKeyboardFor.get(chatId);
        if (shouldRemoveKeyboard != null && shouldRemoveKeyboard)
            attachRemovingKeyboard(chatId, messageToSend);

        sendMessage(messageToSend);
    }

    private void sendSelectorMessage(SelectorResponse response)
    {
        SendMessage messageToSend = new SendMessage();
        messageToSend.enableMarkdown(true);
        messageToSend.setText(response.getPreamble());
        messageToSend.setChatId(response.getReceiverId());
        messageToSend.setReplyMarkup(setupKeyboardMarkup(response, true));

        removeKeyboardFor.put(response.getReceiverId(), true);
        sendMessage(messageToSend);
    }

    private ReplyKeyboardMarkup setupKeyboardMarkup(SelectorResponse response, Boolean isOneTime)
    {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(isOneTime);

        ArrayList<KeyboardRow> rows = new ArrayList<>();
        for(String buttonLabel: response.getOptions())
        {
            KeyboardRow row = new KeyboardRow();
            row.add(buttonLabel);
            rows.add(row);
        }

        keyboardMarkup.setKeyboard(rows);
        return keyboardMarkup;
    }


    private void sendMessage(SendMessage message)
    {
        try
        {
            execute(message);
        }
        catch (TelegramApiException e)
        {
            System.out.println("Exception: " + e.toString());
        }
    }

    private void attachRemovingKeyboard(long userId, SendMessage message)
    {
        ReplyKeyboardRemove removeKeyboard = new ReplyKeyboardRemove();
        message.setReplyMarkup(removeKeyboard);
        removeKeyboardFor.put(userId, false);
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