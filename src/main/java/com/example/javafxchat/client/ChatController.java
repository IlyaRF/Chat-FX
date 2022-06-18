package com.example.javafxchat.client;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatController {

    @FXML
    public TextField messageField;

    @FXML
    public TextArea messageArea;

    public void clickSendButton() {

            final String message = messageField.getText();
            if (message.isBlank()) {
                return;
            }

            messageArea.appendText(message + "\n");
            messageField.clear();
            messageField.requestFocus();

        }
    }
