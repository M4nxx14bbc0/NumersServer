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
public class Number extends Thread{
    private Processo r;
    
    public Number(Processo r){
        super(r);
        this.r = r;
    }
    
    public int getNumber(){
        return r.getNumber();
    }
}
