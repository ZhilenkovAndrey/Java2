package ru.geekbrains;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {

    private final ServerSocket socket;
    private final AuthenticationService authenticationService;
    private final Set<ClientHandler> loggedClients;
    private Socket client;

    public ChatServer() {
        try {
            authenticationService = new AuthenticationService();
            loggedClients = new HashSet<>();
            this.socket = new ServerSocket(8888);

            while (true) {
                System.out.println("Waiting for a new connection...");
                client = socket.accept();
                System.out.println("Client accepted.");
                new Thread(() -> new ClientHandler(client, this)).start();

            }
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong during connection establishing.", e);
        }
    }

    public synchronized AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public synchronized void  addClient(ClientHandler client) {
        loggedClients.add(client);
    }

    public synchronized void removeUserName(ClientHandler client) {
        loggedClients.remove(client);
    }

    public synchronized boolean isUsernameOccupied(String userName) {

        return loggedClients.stream().anyMatch(c -> c.getName().equals(userName));
    }

    public synchronized void broadcastMessage(String message) {
        if(loggedClients.stream().anyMatch(c -> message.startsWith("/w " + c.getName()))) {
            loggedClients.stream()
                    .filter(c -> message.startsWith("/w " + c.getName()))
                    .forEach(c -> c.sendMessage(message.substring(message.lastIndexOf(c.getName()) +
                            c.getName().length())));
        } else {
            loggedClients.stream()
                    .forEach(c -> c.sendMessage(message));
        }
    }
}
