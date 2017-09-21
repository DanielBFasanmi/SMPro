/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpro;

import java.util.ArrayList;
import java.util.HashMap;
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
        saleLog = new HashMap<>();
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

    private void addSaleLog(String[] sale) {
        String pName = sale[2]; //product name
        String operation = sale[0]; //product name
        int qty = Integer.parseInt(sale[1]); // quantity
        double price = Double.parseDouble(sale[3]);
        // check if this product is available in the transaction record
        Sale s = null;
        for(String key : saleLog.keySet()){
        if(key.equalsIgnoreCase(pName)){
            s = saleLog.get(key);     
          //  System.out.println("obj found : "+s.getProductName()+" tot: "+s.getTotal()+" qty: "+s.getQuantity());
            break;
        }
        }
        if(s == null){
            s = new Sale();
            s.setProductName(pName);
            
            s.setTotal(price, Operation.valueOf(operation), qty);        
            saleLog.put(pName, s);
        }else{
            
            s.setTotal(price, Operation.valueOf(operation), qty);
        }
        
    }
    public synchronized void setMessage(String message) {
        this.message = message;         
        String[] processed = MessageProcessor.processMessage(message);
        Message msg = new Message();
        msg.setInfo(message);
        msg.setMessageType(processed[4]);      
        addSaleLog(processed);
        addMessageLog(msg); 
        //rerquirement 3 
        
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
          System.out.println("\t Product Name \t|\t Quantity \t|\t Total Value \t|");
          
          for(String s : saleLog.keySet()){
              Sale sale = saleLog.get(s);
              System.out.println("\t "+sale.getProductName()+" \t|\t "+sale.getQuantity()+" \t|\t "+sale.getTotal()+"(p) \t|");
          }
          System.out.println("------------------------------------------------------------------");
          
    }
 }