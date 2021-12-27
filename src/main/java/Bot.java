import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Bot extends TelegramLongPollingBot {

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){
            handleMassage(update.getMessage());
        }
    }
    @SneakyThrows
    private void handleMassage(Message message) {
        if (message.hasText() && message.hasEntities()){
            Optional<MessageEntity> commandEntity =
                    message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();

            if (commandEntity.isPresent()){
                String command = message.getText().substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
                switch (command){
                    case "/help":
                        execute(SendMessage.builder()
                                .text("Кнопка 1 - делает свое дело."+
                                        "\nКнопка 2 - делает свое дело." +
                                        "\nКнопка 3 - делает свое дело." )
                                .chatId(message.getChatId().toString())
                                .build());
                        break;
                    case "/menu":
                        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
                        for (Buttons buttons1 : Buttons.values()){
                            buttons.add(Arrays.asList(InlineKeyboardButton.builder()
                                    .text(buttons1.getId()).callbackData("ORIGINAL:" + buttons1)
                                    .build()));
                        }
                        execute(SendMessage.builder()
                                .text("Что бы вы хотели сделать?")
                                .chatId(message.getChatId().toString())
                                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                                .build());
                        break;
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "@BoGeVo_Bot";
    }

    @Override
    public String getBotToken() {
        return "5099407036:AAEGxZm_aT1sFDK7HYXKs_TMctb3Igkeh3o";
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }



    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }
    @SneakyThrows
    public static void main(String[] args) {
        Bot bot = new Bot();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
    }
}