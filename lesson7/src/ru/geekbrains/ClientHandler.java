package ru.geekbrains;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientHandler {

    private Socket socket;
    private ChatServer server;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;

    public ClientHandler(Socket socket, ChatServer server) {
        this.socket = socket;
        this.server = server;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            throw new RuntimeException("Something went wring during a client connection establishing.");
        }

        doAuthentication();
        listenMessages();
    }

    public String getName() {
        return name;
    }

    private void doAuthentication() {
        try {
            performAuthentication();
        } catch (IOException ex) {
            throw new RuntimeException("Something went wring during a client authentication.");
        }
    }

    private void performAuthentication() throws IOException {
        while (true) {
            out.writeUTF("Enter: '-auth', 'your login', 'your password' through a space");
            String inboundMessage = in.readUTF();
            if (inboundMessage.startsWith("-auth")) {
                // valid request sample: -auth l1 p1
                String[] credentials = inboundMessage.split("\\s");

                AtomicBoolean isSuccess = new AtomicBoolean(false);
                server.getAuthenticationService()
                        .findUsernameByLoginAndPassword(credentials[1], credentials[2])
                        .ifPresentOrElse(
                                username -> {
                                    if (!server.isUsernameOccupied(username)) {
                                        name = username;
                                        server.broadcastMessage(String.format("User[%s] is logged in", name));
                                        server.addClient(this);
                                        isSuccess.set(true);
                                    } else {
                                        sendMessage("Current username is already occupied.");
                                    }
                                },
                                () -> sendMessage("Bad credentials.")
                        );

                if (isSuccess.get()) break;
            }
        }
    }

    public void sendMessage(String outboundMessage) {
        try {
            out.writeUTF(outboundMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMessage() {
        try {
            server.broadcastMessage(in.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenMessages() {
        while (true) {
            readMessage();
        }
    }
}
