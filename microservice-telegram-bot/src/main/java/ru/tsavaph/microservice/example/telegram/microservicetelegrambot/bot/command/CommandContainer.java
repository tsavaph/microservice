package ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.commands.*;
import ru.tsavaph.microservice.example.telegram.microservicetelegrambot.proxy.CurrencyCalculatorServiceProxy;

import static ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.commands.CommandName.*;

@Component
public class CommandContainer {
    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;


    @Autowired
    public CommandContainer(CurrencyCalculatorServiceProxy proxy) {

        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand())
                .put(STOP.getCommandName(), new StopCommand())
                .put(HELP.getCommandName(), new HelpCommand())
                .put(NO.getCommandName(), new NoCommand())
                .put(CURRENCY.getCommandName(), new CurrencyCommand(proxy))
                .build();

        unknownCommand = new UnknownCommand();
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
