package main;

import main.Data.IAppRepository;
import main.Data.InMemoryRepository;
import main.IO.*;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class TelegramProgram
{
    private static String PROXY_HOST = "207.154.222.224";
    private static Integer PROXY_PORT = 1080;
    private static String PROXY_USER;
    private static String PROXY_PASSWORD;

    public static void main(String[] args)
    {
        IAppRepository repository = new InMemoryRepository();
        PROXY_USER = args[0];
        PROXY_PASSWORD = args[1];

        Bot chatBot = new Bot(repository);
        try
        {
            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(PROXY_USER, PROXY_PASSWORD.toCharArray());
                }
            });

            ApiContextInitializer.init();
            TelegramBotsApi botsApi = new TelegramBotsApi();

            DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);
            options.setProxyHost(PROXY_HOST);
            options.setProxyPort(PROXY_PORT);
            options.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
            botsApi.registerBot(new TelegramBot(options, chatBot));
        }
        catch (TelegramApiException e)
        {
            e.printStackTrace();
        }
    }
}
