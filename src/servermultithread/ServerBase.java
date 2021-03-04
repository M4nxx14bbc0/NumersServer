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
            InputStream dalClient = clientSocket.getInputStream(); 
            
            BufferedReader lettore = new BufferedReader(new InputStreamReader(dalClient));
            String messaggio = "";
            
            System.out.println("serverino in ascolto...");
            messaggio = lettore.readLine();
            lettore.close();
            dalClient.close();
            
            int numero = Integer.parseInt(messaggio);
            
            Number[] t = new Number[numero];
            for (int i = 0; i < t.length; i++) {
                t[i] = new Number(new Processo(i+1));
                t[i].start();
            }
            numero = 0;
            for (int i = 0; i < t.length; i++) {
                numero += t[i].getNumber();
                t[i].stop();
            }
            System.out.println("Risultato : "+numero);
            clientSocket.close();

            System.out.println("chiusura connessione effettuata");
            Thread.sleep(5000);
        } catch (InterruptedException i) {
        } catch (IOException ex) {
            Logger.getLogger(ServerBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
