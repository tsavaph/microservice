package ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.CommandContainer;

import static ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.commands.Command.COMMAND_PREFIX;
import static ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.commands.CommandName.NO;

@Service
public class SendBotMessageServiceImpl implements SendBotMessageService {

    private final CommandContainer commandContainer;

    @Autowired
    public SendBotMessageServiceImpl(CommandContainer commandContainer) {
        this.commandContainer = commandContainer;
    }

    @Override
    public SendMessage getSendMessage(Update update) {

        SendMessage sendMessage = null;

        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();

            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();

                sendMessage = commandContainer.retrieveCommand(commandIdentifier).getSendMessage(update);
            } else {
                sendMessage = commandContainer.retrieveCommand(NO.getCommandName()).getSendMessage(update);
            }
        }

        return sendMessage;
    }
}
