package com.example.javafxchat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

    private List<ClientHandler> clients;
    public ChatServer() {
        this.clients = new ArrayList<>();

    }

    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(8189);
            AuthService authService = new inMemoryAuthService()) {
            while (true) {
                System.out.println("Ожидание подключения...");
                Socket socket = serverSocket.accept();
                new ClientHandler(socket, this, authService);
                System.out.println("Клиент подключен");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void broadcast(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
    }
}
