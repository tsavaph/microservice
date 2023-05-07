package ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command {
    public final static String START_MESSAGE = "Привет. Start";


    @Override
    public SendMessage getSendMessage(Update update) {
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
        sendMessage.enableHtml(true);
        return sendMessage;
    }
}
