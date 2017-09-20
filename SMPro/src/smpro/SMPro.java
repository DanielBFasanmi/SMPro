/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpro;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 1412625
 */
public class SMPro {
    private  boolean flag = true;
    private  List<Message> messageLog;
    private  Map<String,Sale> saleLog;

    
    private String message;
    
    public SMPro() {
        this.messageLog = new ArrayList<>();
    }

    public synchronized boolean isFlag() {
        return flag;
    }

    private void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<Message> getMessageLog() {
        return messageLog;
    }

    private void addMessageLog(Message mLog) {
        this.messageLog.add(mLog);
    }
    public List<Message> getSaleLog() {
        return messageLog;
    }

    private void addSaleLog(String key, Sale sLog) {
        this.saleLog.put(key, sLog);
    }
    public synchronized void setMessage(String message) {
        this.message = message;         
        String[] processed = MessageProcessor.processMessage(message);
        Message msg = new Message();
        msg.setInfo(message);
        msg.setMessageType(processed[4]); 
        addMessageLog(msg); 
        messageLogSizeListener();
    }
    private void messageLogSizeListener(){
     if (messageLog.size() >= 50){  
         flag = false; 
         detailLogPrinter();
         messageLogPrinter();
     }else if(messageLog.size() % 10 == 0){
         detailLogPrinter();
     }
    }

    private void messageLogPrinter() {
        // prints all the
         System.out.println("------------------------------------------------------------------");
         System.out.println("pausing.........No new message will be acepted.........");
         System.out.println("----------------Log for all adjustment made to each sale--------------------");
         messageLog.stream()
                    .filter((m)->m.getMessageType().equals("2"))
                    .forEach(System.out::println);
         
    }

    private void detailLogPrinter() {
          System.out.println("------------------------------------------------------------------");
          System.out.println("Printing Report for the last "+messageLog.size()+" transactions!");
          System.out.println("------------------------------------------------------------------");
          
    }
 }