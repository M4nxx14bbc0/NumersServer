/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servermultithread;

/**
 *
 * @author parrarodriguez.manue
 */
public class Processo implements Runnable{
    private boolean stop = false;
    private int s;
    private static int constant = 0;
    
    public Processo(int s){
        this.s = s;
    }
    
    @Override
    public void run(){
        for (int i = 0;; i++) {
            if(stop==true)
                break;
            s += constant;
        }
    }
    
    public int stop(){
        stop = true;
        return s;
    }
}
