/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpro;
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author 1412625
 */
public class TransactionMessages {

    private static List<String> produts = new ArrayList();
    static{
        produts.add("apple");
        produts.add("banana");
        produts.add("mango");
        produts.add("pear");
    }
    
    public static void main(String[] args) {
        System.out.println(createTransactionMessage());
    }

    public static List<String> getProduts() {
        return produts;
    }

    public static void setProduts(List<String> produts) {
        TransactionMessages.produts = produts;
    }
    public static String createTransactionMessage() {
        
        String messageType = "";
        Random price = new Random();
        Random quantity = new Random();
        Random selector = new Random();
        Random productIndex = new Random();
   
        switch(selector.nextInt(3) +1){       
            case 1:
                messageType = produts.get(productIndex.nextInt(produts.size()))+" at "+(price.nextInt(200) + 1)+"p";
                break;
                
            case 2:
                messageType = (quantity.nextInt(200)+1)+" sales of "+produts.get(productIndex.nextInt(produts.size()))+" at "+(price.nextInt(50) + 1)+"p each";
                break;
                
            default:
                Random getOperation = new Random();
                int val = getOperation.nextInt(3) + 1;
        switch (val) {
            case 1:
                messageType = "Add";
                break;
            case 2:
                messageType = "Subtract";
                break;
            default:
                messageType = "Multiply";
                break;
        }
                messageType += " "+(price.nextInt(50) + 1)+"p "+produts.get(productIndex.nextInt(produts.size()));
                break;
        
        }
        return  messageType;
        
    }
           
}
