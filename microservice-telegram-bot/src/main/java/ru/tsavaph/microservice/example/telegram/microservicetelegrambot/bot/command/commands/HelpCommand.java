package ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.commands.CommandName.*;

public class HelpCommand implements Command {

    public static final String HELP_MESSAGE = String.format("""
                    ✨<b>Доступные команды</b>✨

                    <b>Начать\\закончить работу с ботом</b>
                    %s - начать работу со мной
                    %s - приостановить работу со мной

                    %s - получить помощь в работе со мной
                    """,
            START.getCommandName(), STOP.getCommandName(), HELP.getCommandName());


    @Override
    public SendMessage getSendMessage(Update update) {
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
        sendMessage.enableHtml(true);
        return sendMessage;
    }
}
