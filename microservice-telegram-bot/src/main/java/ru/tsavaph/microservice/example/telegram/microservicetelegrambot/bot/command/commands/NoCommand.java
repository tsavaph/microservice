package ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.commands.CommandName.HELP;
import static ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.commands.CommandName.START;

public class NoCommand implements Command {
    public final static String NO_COMMAND_MESSAGE = String.format(
            "Command must start with \"%s\". Type %s to see all available commands.",
            COMMAND_PREFIX, HELP.getCommandName()
    );

    @Override
    public SendMessage getSendMessage(Update update) {
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId().toString(), NO_COMMAND_MESSAGE);
        sendMessage.enableHtml(true);
        return sendMessage;
    }
}
