package ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.commands;

import feign.FeignException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bean.CurrencyConversionBean;
import ru.tsavaph.microservice.example.telegram.microservicetelegrambot.proxy.CurrencyCalculatorServiceProxy;
import java.math.BigDecimal;

import static ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.commands.CommandName.CURRENCY;
import static ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.commands.CommandName.HELP;

public class CurrencyCommand implements Command {
    private final CurrencyCalculatorServiceProxy proxy;
    public static final String INCORRECT_CURRENCY_COMMAND = String.format(
            """
            Incorrect currency command. Correct are:
                %s from to year month day amount;
                %s from to year month amount.
            Type %s to see examples.
            """,
            CURRENCY.getCommandName(), CURRENCY.getCommandName(), HELP.getCommandName()
    );

    public CurrencyCommand(CurrencyCalculatorServiceProxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public SendMessage getSendMessage(Update update) {
        String sendMessageString = getSendMessageString(update);
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId().toString(), sendMessageString);
        sendMessage.enableHtml(true);
        return sendMessage;
    }

    private String getSendMessageString(Update update) {
        String[] updateMessageValues = update.getMessage().getText().trim().split(" ");
        String sendMessageString;
        if (updateMessageValues.length == proxy.countRetrieveExchangeValuePathVariables() + 1) {
            try {
                CurrencyConversionBean currencyConversionBean = proxy.retrieveExchangeValue(
                        updateMessageValues[1],
                        updateMessageValues[2],
                        Integer.parseInt(updateMessageValues[3]),
                        Integer.parseInt(updateMessageValues[4]),
                        Integer.parseInt(updateMessageValues[5]),
                        new BigDecimal(Integer.parseInt(updateMessageValues[6]))
                );

                sendMessageString = currencyConversionBean.getTotalCalculatedAmount().toString();
            } catch (FeignException.FeignClientException e) {

                sendMessageString = parseFeignException(e);
            }


        } else if (updateMessageValues.length == proxy.countRetrieveAvgExchangeValuePathVariables() + 1) {

            try {
                CurrencyConversionBean currencyConversionBean = proxy.retrieveAvgExchangeValue(
                        updateMessageValues[1],
                        updateMessageValues[2],
                        Integer.parseInt(updateMessageValues[3]),
                        Integer.parseInt(updateMessageValues[4]),
                        new BigDecimal(Integer.parseInt(updateMessageValues[5]))
                );

                sendMessageString = currencyConversionBean.getTotalCalculatedAmount().toString();
            } catch (FeignException.FeignClientException e) {
                sendMessageString = parseFeignException(e);
            }

        } else {
            sendMessageString = INCORRECT_CURRENCY_COMMAND;
        }

        return sendMessageString;
    }

    private String parseFeignException(FeignException.FeignClientException e) {
        String exceptionMessage = e.getMessage();
        int startIndex = exceptionMessage.lastIndexOf(":[\"");
        int endIndex = exceptionMessage.lastIndexOf("\"]}]");

        return exceptionMessage.substring(startIndex + 3, endIndex);
    }
}
