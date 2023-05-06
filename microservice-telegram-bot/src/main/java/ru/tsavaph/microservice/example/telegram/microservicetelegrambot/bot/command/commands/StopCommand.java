package ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StopCommand implements Command {
    public final static String STOP_MESSAGE = "Привет. Stop";


    @Override
    public SendMessage getSendMessage(Update update) {
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId().toString(), STOP_MESSAGE);
        sendMessage.enableHtml(true);
        return sendMessage;
    }
}
