package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
public class MyThread extends Thread{
    private Socket s;
    public MyThread(Socket s) {
        this.s = s;
    }
    @Override
    public void run() {    
        try(
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true)
        ) {
            String inputLine;
            while ((inputLine=in.readLine())!=null) {
                if (inputLine.equals("!")) {
                    break;
                }else if(inputLine.equals("?")){
                    List<String> notes = Main.getNotes();
                    for(String note : notes){
                        out.println(note);
                    }
                    out.println("@");
                }else{
                    Main.addNote(inputLine);
                    out.println("OK");
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                s.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
