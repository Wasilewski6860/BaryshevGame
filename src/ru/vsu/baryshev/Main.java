package ru.vsu.baryshev;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
