/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servermultithread;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author parrarodriguez.manue
 */
public class ServerBase implements Runnable {
    private static Socket clientSocket;

    public ServerBase(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        System.out.println("Client collegato: " + clientSocket.getInetAddress());
        try {
            InputStream dalServer = clientSocket.getInputStream();
            OutputStream alServer = clientSocket.getOutputStream(); 
            
            PrintWriter scrittore = new PrintWriter(alServer, true);
            BufferedReader lettore = new BufferedReader(new InputStreamReader(dalServer));
            String messaggio = "";
            
            System.out.println("serverino in ascolto...");
            messaggio = lettore.readLine();
            
            int numero = Integer.parseInt(messaggio);
            
            Thread[] t = new Thread[numero];
            for (int i = 0; i < t.length; i++) {
                t[i] = new Thread(new Processo(i+1));
                t[i].start();
            }
            
            
            
            System.out.println("stringa dal client: " + numero);
            scrittore.println(messaggio + ": "+messaggio.length());

            scrittore.close();
            clientSocket.close();

            System.out.println("chiusura connessione effettuata");
            Thread.sleep(5000);
        } catch (InterruptedException i) {
        } catch (IOException ex) {
            Logger.getLogger(ServerBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
