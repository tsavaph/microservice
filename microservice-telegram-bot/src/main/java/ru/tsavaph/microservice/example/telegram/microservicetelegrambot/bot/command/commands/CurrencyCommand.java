package ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.commands;

import feign.FeignException;
import org.springframework.util.NumberUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bean.CurrencyConversionBean;
import ru.tsavaph.microservice.example.telegram.microservicetelegrambot.proxy.CurrencyCalculatorServiceProxy;
import ru.tsavaph.microservice.example.telegram.microservicetelegrambot.utils.NumberFormatters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.commands.CommandName.CURRENCY;
import static ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot.command.commands.CommandName.HELP;

public class CurrencyCommand implements Command {
    private final CurrencyCalculatorServiceProxy proxy;
    public static final String INCORRECT_CURRENCY_COMMAND = String.format(
            """
            Incorrect currency command.
            Correct are:
                %s from to year month day amount;
                %s from to year month amount,
            where
                from - 3 length string like usd or EUR,
                to - 3 length string like usd or EUR,
                year - 4 length integer,
                month - integer from 1 to 12,
                day - integer from 1 to 31,
                amount - floating point number.
            
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
                        new BigDecimal(updateMessageValues[6])
                );

                sendMessageString = buildSendMessageStrings(currencyConversionBean);
            } catch (FeignException.FeignClientException e) {
                sendMessageString = parseFeignException(e);
            } catch (NumberFormatException e) {
                sendMessageString = INCORRECT_CURRENCY_COMMAND;
        }


        } else if (updateMessageValues.length == proxy.countRetrieveAvgExchangeValuePathVariables() + 1) {

            try {
                CurrencyConversionBean currencyConversionBean = proxy.retrieveAvgExchangeValue(
                        updateMessageValues[1],
                        updateMessageValues[2],
                        Integer.parseInt(updateMessageValues[3]),
                        Integer.parseInt(updateMessageValues[4]),
                        new BigDecimal(updateMessageValues[5])
                );

                sendMessageString = buildSendMessageStrings(currencyConversionBean);
            } catch (FeignException.FeignClientException e) {
                sendMessageString = parseFeignException(e);
            } catch (NumberFormatException e) {
                sendMessageString = INCORRECT_CURRENCY_COMMAND;
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

    private String buildSendMessageStrings(CurrencyConversionBean currencyConversionBean) {

        BigDecimal currencyConversion = NumberFormatters.formatBigDecimal(
                currencyConversionBean.getTotalCalculatedAmount(), 4);

        int monthValue = currencyConversionBean.getMonth();
        int yearValue = currencyConversionBean.getYear();
        int dayValue = 1;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.yyyy");

        if (currencyConversionBean.getDay() != null) {
            dayValue = currencyConversionBean.getDay();
            formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        }

        LocalDate date = LocalDate.of(yearValue, monthValue, dayValue);
        String formattedDate = date.format(formatter);


        return String.format("%s %s was equal to %s %s in %s",
                currencyConversionBean.getQuantity(),
                currencyConversionBean.getFrom(),
                currencyConversion,
                currencyConversionBean.getTo(),
                formattedDate);
    }
}
