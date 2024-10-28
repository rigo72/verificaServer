package com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException{
        System.out.println("SERVER AVVIATO");

        ServerSocket ss = new ServerSocket(8647);

        do {
            Socket socket = ss.accept();
            MyThread t = new MyThread(socket);
            t.start(); 
        } while(true);
    }
}