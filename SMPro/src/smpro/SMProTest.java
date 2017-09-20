/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpro;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1412625
 */
public class SMProTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SMPro smp = new SMPro();
        for(char c = 'A'; c <'K'; c++){
            Terminal terminal = new Terminal(""+c, smp);
            Thread t = new Thread(terminal);
            t.start();
            
            
            
        }
    }
  
}
class Terminal implements Runnable{
 private final String name;
 private  boolean flagger = false;
    private SMPro smp;
    public Terminal(String name, SMPro smp) { 
        this.name = "Terminal" + name;
        this.smp = smp;
    }

    public SMPro getSmp() {
        return smp;
    }

    public void setSmp(SMPro smp) {
        this.smp = smp;
    }
    
    @Override
    public void run() {
        do{
            try {
                Thread.sleep(1000);
                if(smp.isFlag()){
                    smp.setMessage(TransactionMessages.createTransactionMessage());
                }else{
                    flagger = true;
                    System.out.println(name+": we are pausing...");
                }   } catch (InterruptedException ex) {
                Logger.getLogger(Terminal.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }while(!flagger);
            
        
    }
 @Override
    public String toString(){
    return name;
    }

}