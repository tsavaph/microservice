package ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NoCommand implements Command {
    public final static String NO_COMMAND_MESSAGE = "Нет такой команды";

    @Override
    public SendMessage getSendMessage(Update update) {
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId().toString(), NO_COMMAND_MESSAGE);
        sendMessage.enableHtml(true);
        return sendMessage;
    }
}
