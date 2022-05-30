package ru.geekbrains;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner scanner;
    private String outboundMessage;
    private String inboundMessage;

    public Client() {
        try {
            this.socket = new Socket("127.0.0.1", 8888);
            this.scanner = new Scanner(System.in);
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start() throws IOException {
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        new Thread(() -> {
            while (true) {
                try {
                    inboundMessage = in.readUTF();
                    if (inboundMessage != null) {
                        System.out.println("\nIncoming message:\n" + inboundMessage + "\n\nPlease enter a message:");
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed.");
                    break;
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    outboundMessage = scanner.nextLine();
                    out.writeUTF(outboundMessage);
                } catch (IOException e) {
                    System.out.println("Connection closed.");
                    break;
                }
            }
        }).start();
    }
}

