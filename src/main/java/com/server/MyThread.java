package com.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MyThread extends Thread{

    private static int thread_count = 0;
    private Socket s;

    public MyThread(Socket s) {
        thread_count++;
        System.out.println("Thread - " + thread_count + " - creato");
        this.s = s;
    }

    public void run() {    
                     // Metodo run() contiene il codice asoncrono di questo thread
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
            DataOutputStream out = new DataOutputStream(this.s.getOutputStream());
            int received_choice;
            do {
                String received_string = in.readLine();
                received_choice = Integer.parseInt(in.readLine());

                System.out.println(received_string);
                System.out.println(received_choice);
                String editedString = editString(received_string, received_choice);
                
                out.writeBytes(editedString + '\n');
                System.out.println("** AGGIORNATO E INVIATO LA STRINGA MODIFICATA**");
            } while(received_choice >= 1 || received_choice <= 4);
        } catch (IOException e) {
            System.out.println("ERRORE NELLA COMUNICAZIONE");
        }
    }

    private String editString(String s, int c) {
        switch(c) {
            case 1:
                return s.toUpperCase();
            case 2:
                return s.toLowerCase();
            case 3:
                return new StringBuilder(s).reverse().toString();
            case 4:
                return String.valueOf(s.length());
            default:
                System.out.println("Scelta non valida");
                return null;
        }
    }

}
