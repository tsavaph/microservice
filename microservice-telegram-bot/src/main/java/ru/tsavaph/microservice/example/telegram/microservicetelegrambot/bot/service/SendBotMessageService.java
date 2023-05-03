package ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.service;

public interface SendBotMessageService {
    void sendMessage(String chatId, String message);
}
