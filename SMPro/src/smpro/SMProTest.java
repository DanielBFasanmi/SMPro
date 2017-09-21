/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpro;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1412625
 * this class contains the main method of the application
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
           //terminal.run();
        }
    }
  
}
/**the terminal class act as terminal(external company) that sends messages to the application 
 * the message sent are generated from Transaction Message class
 * the terminal thread runs in a while loop until a condition is met 
 * it prints out "we are pausing"
 * 
 * *
 * @author 1412625
 */
class Terminal implements Runnable{
 private final String name;
 private  boolean flagger = false;
    private SMPro smp;
    public Terminal(String name, SMPro smp) { 
        this.name = "Terminal" + name;
        this.smp = smp;
    }

    
    @Override
    public void run() {
        do{
            try {
                Random r = new Random();
                Thread.sleep(r.nextInt(5000));
                if(smp.isFlag()){
                    //RegExer.main(TransactionMessages.createTransactionMessage());
                    String sr = TransactionMessages.createTransactionMessage();
                    System.out.println(name+": sending ... "+sr);
                    smp.setMessage(sr);
                    
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