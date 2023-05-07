package ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bean.CurrencyConversionBean;
import ru.tsavaph.microservice.example.telegram.microservicetelegrambot.proxy.CurrencyCalculatorServiceProxy;

import java.math.BigDecimal;

public class CurrencyCommand implements Command {

    private final CurrencyCalculatorServiceProxy proxy;

    public static final String INCORRECT_CURRENCY_COMMAND = "incorrect currency command";

    public CurrencyCommand(CurrencyCalculatorServiceProxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public SendMessage getSendMessage(Update update) {
        String[] updateMessageValues = update.getMessage().getText().trim().split(" ");
        String sendMessageString;

        if (updateMessageValues.length == proxy.countRetrieveExchangeValuePathVariables() + 1) {
            CurrencyConversionBean currencyConversionBean = proxy.retrieveExchangeValue(
                    updateMessageValues[1],
                    updateMessageValues[2],
                    Integer.parseInt(updateMessageValues[3]),
                    Integer.parseInt(updateMessageValues[4]),
                    Integer.parseInt(updateMessageValues[5]),
                    new BigDecimal(Integer.parseInt(updateMessageValues[6]))
            );

            sendMessageString = currencyConversionBean.getTotalCalculatedAmount().toString();

        } else if (updateMessageValues.length == proxy.countRetrieveAvgExchangeValuePathVariables() + 1) {
            CurrencyConversionBean currencyConversionBean = proxy.retrieveAvgExchangeValue(
                    updateMessageValues[1],
                    updateMessageValues[2],
                    Integer.parseInt(updateMessageValues[3]),
                    Integer.parseInt(updateMessageValues[4]),
                    new BigDecimal(Integer.parseInt(updateMessageValues[5]))
            );

            sendMessageString = currencyConversionBean.getTotalCalculatedAmount().toString();

        } else {
            sendMessageString = INCORRECT_CURRENCY_COMMAND;
        }

        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId().toString(), sendMessageString);
        sendMessage.enableHtml(true);
        return sendMessage;
    }
}