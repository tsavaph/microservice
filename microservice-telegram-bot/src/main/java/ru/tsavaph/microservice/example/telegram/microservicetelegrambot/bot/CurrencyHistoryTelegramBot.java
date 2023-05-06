package ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.service.SendBotMessageServiceImpl;

@Component
public class CurrencyHistoryTelegramBot extends TelegramLongPollingBot {

    private final SendBotMessageServiceImpl sendMessageService;

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    @Autowired
    public CurrencyHistoryTelegramBot(SendBotMessageServiceImpl sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage =  sendMessageService.getSendMessage(update);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
