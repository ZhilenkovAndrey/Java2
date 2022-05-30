package ru.geekbrains;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Chat extends JFrame{
    private final JFrame mainFrame;
    private JButton jButton;
    private JTextArea textArea;
    private JPanel top1;
    private JTextField textField;
    private JPanel top;


    public Chat() {
        mainFrame = new JFrame();
        mainFrame.setTitle("CHAT v1.0");
        mainFrame.setBounds(new Rectangle(300, 500));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);

        top = new JPanel();
        textField = new JTextField();
        top.setLayout(new BorderLayout());
        top.add(textField, BorderLayout.NORTH);
        mainFrame.add(top, BorderLayout.NORTH);

        jButton = new JButton("SEND");
        jButton.setLayout(new BorderLayout());
        top.add(jButton, BorderLayout.SOUTH);
        mainFrame.add(top, BorderLayout.SOUTH);

        top1 = new JPanel();
        textArea = new JTextArea();
        top1.setLayout(new BorderLayout());
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        top1.add(textArea, BorderLayout.CENTER);
        mainFrame.add(top1, BorderLayout.CENTER);

        mainFrame.setVisible(true);
        textField.grabFocus();

        jButton.addActionListener(e -> {
            sendMessage();
            });

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });
    }

    private void sendMessage() {
            textArea.setText(new StringBuilder(textArea.getText())
                   .append(textField.getText()).toString() + "\n");
            textField.setText(null);
            textField.grabFocus();
    }
}
