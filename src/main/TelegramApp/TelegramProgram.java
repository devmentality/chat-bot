package main.TelegramApp;

import main.Data.ChallengeRepository;
import main.Data.ConcurrentInMemoryRepo;
import main.Data.ConcurrentNewInMemoryRepo;
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
    private static String BOT_TOKEN;

    public static void main(String[] args)
    {
        ConcurrentNewInMemoryRepo repository = new ConcurrentNewInMemoryRepo();
        ChallengeRepository challengeRepository = new ChallengeRepository();
        BOT_TOKEN = args[0];
        PROXY_USER = args[1];
        PROXY_PASSWORD = args[2];

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
            botsApi.registerBot(new TelegramBotsManager(BOT_TOKEN, options, repository, challengeRepository));
        }
        catch (TelegramApiException e)
        {
            e.printStackTrace();
        }
    }
}
