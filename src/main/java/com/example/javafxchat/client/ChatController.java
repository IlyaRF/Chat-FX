package com.example.javafxchat.client;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;

public class ChatController {

    @FXML
    private TextField messageField;

    @FXML
    private TextArea messageArea;

    private final ChatClient client;

    public ChatController() {
        this.client = new ChatClient(this);
        while (true) {
            try {
                client.openConnection();
                break;
            } catch (IOException e) {
                showNotification();
            }
        }
    }

    private void showNotification() {
        final Alert alert = new Alert(Alert.AlertType.ERROR,
                "Не могу подключиться к серрверу",
                new ButtonType("Попробовать снова", ButtonBar.ButtonData.OK_DONE),
                new ButtonType("Выйти", ButtonBar.ButtonData.CANCEL_CLOSE)
        );
        alert.setTitle("Ошибка подключения");
        Optional<ButtonType> answer = alert.showAndWait();
        Boolean isExit = answer.map(select -> select.getButtonData().isCancelButton()).orElse(false);
    }

    public void clickSendButton() {

        final String message = messageField.getText();
        if (message.isBlank()) {
            return;
        }

        client.sendMessage(message);
        messageField.clear();
        messageField.requestFocus();

    }

    public void addMessage(String message) {
        if (message.isBlank()) {
            return;
        }
    }
}
