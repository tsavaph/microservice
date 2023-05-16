package ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.commands.CommandName.HELP;

public class StartCommand implements Command {
    public final static String START_MESSAGE = String.format(
            "This is a bot that helps you to know historical currency values. Type %s to see all available commands.",
            HELP.getCommandName()
    );
    private final static String HELLO_LINE = "Hi";


    @Override
    public SendMessage getSendMessage(Update update) {
        String username = update.getMessage().getChat().getFirstName();
        String message = String.format("%s, %s!\n%s", HELLO_LINE, username, START_MESSAGE);
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId().toString(), message);
        sendMessage.enableHtml(true);
        return sendMessage;
    }
}
