package com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
public class Main {
    private static List<String> notes = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        System.out.println("SERVER AVVIATO");
        try(ServerSocket ss = new ServerSocket(3000)){
            while (true) {
                Socket clienSocket = ss.accept();
                System.out.println("Nuovo Client Collegato");
                new Thread(new MyThread(clienSocket)).start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static synchronized void addNote(String note){
        notes.add(note);
    }
    public static synchronized List<String> getNotes(){
        return notes;
    }
}