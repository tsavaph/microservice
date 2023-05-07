package ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface SendBotMessageService {
    SendMessage getSendMessage(Update update);
}
