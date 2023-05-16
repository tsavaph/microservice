package ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.commands.CommandName.*;

public class HelpCommand implements Command {

    public static final String HELP_MESSAGE = String.format("""
                    ✨<b>Available commands</b>✨
                    
                    %s - start a bot
                    %s - stop a bot
                    %s - get help
                    %s from to year month {day} amount - get historical currency info
                    
                    <b>Example of %s command</b>
                    
                    1) Average currency by month
                       <b>%s usd rub 2021 12 1000</b>
                            <b>usd</b> - converted from,
                            <b>rub</b> - converted to,
                            <b>2021</b> - year,
                            <b>12</b> - number of month (December),
                            <b>1000</b> - amount.
                            
                    2) Currency by day
                       <b>%s usd rub 2018 4 10 2000</b>
                            <b>usd</b> - converted from,
                            <b>rub</b> - converted to,
                            <b>2018</b> - year,
                            <b>4</b> - number of month (April),
                            <b>10</b> - number of day,
                            <b>2000</b> - amount.
                    """,
            START.getCommandName(), STOP.getCommandName(), HELP.getCommandName(), CURRENCY.getCommandName(),
            CURRENCY.getCommandName(), CURRENCY.getCommandName(), CURRENCY.getCommandName());


    @Override
    public SendMessage getSendMessage(Update update) {
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
        sendMessage.enableHtml(true);
        return sendMessage;
    }
}
