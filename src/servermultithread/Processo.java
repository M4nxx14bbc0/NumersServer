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
    
    public Processo(int s){
        this.s = s;
    }
    
    @Override
    public void run(){
        System.out.println(s);
        stop();
    }
    
    public void stop(){
        stop = true;
    }
    
    public int getNumber(){
        return s;
    }
}
