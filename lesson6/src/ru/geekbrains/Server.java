package ru.geekbrains;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private ServerSocket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String message;
    private Scanner scanner;
    private String outboundMessage;
    private String inboundMessage;


    public Server() {
        try {
            this.socket = new ServerSocket(8080);
            this.scanner = new Scanner(System.in);
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start() throws IOException {
        System.out.println("Server socket started...");
        System.out.println("Server socket is waiting for a connection...");
        Socket accepted = socket.accept();
        System.out.println("Client connected...");
        System.out.println("Address: " + accepted.getInetAddress());

        in = new DataInputStream(accepted.getInputStream());
        out = new DataOutputStream(accepted.getOutputStream());

        new Thread(() -> {
            while (true) {
                try {
                    message = "";
                    outboundMessage = "";
                    System.out.println("\nPlease enter a message:\nWhen you finish a message, print 'send'");
                    while (!outboundMessage.equalsIgnoreCase("send")) {
                        message += outboundMessage + "\n";
                        outboundMessage = scanner.nextLine();
                    }
                    out.writeUTF(message);
                } catch (Exception e) {
                    throw new MyServerException("Something went wrong during message write-operation.", e);
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    inboundMessage = in.readUTF();
                    System.out.println("\nIncoming message:\n" + inboundMessage + "\nPlease enter message:" +
                            "\nWhen you finish a message, print 'send'");
                } catch (EOFException ex) {
                    throw new MyServerException("End of stream reached unexpectedly. Probably, client is shutdown.", ex);
                } catch (IOException ex) {
                    throw new MyServerException("Something went wrong during inbound message read-operation.", ex);
                } catch (MyServerException ex) {
                    System.out.println("The client is gone away.");
                    System.out.println("Connection gracefully closed.");
                    System.out.println("NOTE: Check log files for a detail information.");
                }
            }
        }).start();
    }
}


