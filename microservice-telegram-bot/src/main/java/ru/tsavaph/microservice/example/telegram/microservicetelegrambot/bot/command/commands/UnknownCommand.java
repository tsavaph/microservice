package ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.commands.CommandName.HELP;

public class UnknownCommand implements Command {
    public static final String UNKNOWN_MESSAGE = String.format("Не понимаю вас \uD83D\uDE1F, напишите %s чтобы узнать что я понимаю.", HELP.getCommandName());


    @Override
    public SendMessage getSendMessage(Update update) {
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId().toString(), UNKNOWN_MESSAGE);
        sendMessage.enableHtml(true);
        return sendMessage;
    }
}
