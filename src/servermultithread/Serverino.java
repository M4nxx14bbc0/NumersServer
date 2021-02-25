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
public class Serverino implements Runnable {
    private Socket clientSocket;

    public Serverino(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        System.out.println("Clinet collegato: " + clientSocket.getInetAddress());
        try {
            PrintWriter scrittore = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader lettore = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String messaggio = "";
            while (!messaggio.equals("exit")) {
                System.out.println("serverino in ascolto...");
                messaggio = lettore.readLine();
                System.out.println("stringa dal client: " + messaggio);
                scrittore.println(messaggio.toUpperCase());
            }

            scrittore.close();
            clientSocket.close();

            System.out.println("chiusura connessione effettuata");

        } catch (IOException ex) {
            Logger.getLogger(Serverino.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
