package ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {
    void execute(Update update);
}
