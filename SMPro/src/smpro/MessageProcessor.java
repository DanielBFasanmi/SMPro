/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpro;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author 1412625
 */
public class MessageProcessor {

    public static String[] processMessage(String message){
    String[] processed = new String[5];
      String line = "Add 20p apples";
      //String line = "Add 20p apples";
      String pattern = "^(([a-zA-Z0-9_']+) at (\\d+)p)$|(^(\\d+) sales of ([a-zA-Z0-9_']+) at (\\d+)p)$|^((Add|Subtract|Multiply) (\\d+)p ([a-zA-Z0-9_']+))$";

      // Create a Pattern object
      Pattern r = Pattern.compile(pattern);

      // Now create matcher object.
      Matcher matcher = r.matcher(line);
      if (matcher.find()) {
          if(matcher.group(1) != null){
          processed[0] = "Insert";//operation
          processed[1] = "1";//quantity
          processed[2] = matcher.group(2);//product
          processed[3] = matcher.group(3);//unit price
          processed[4] = "1";//message type
          
          }else if(matcher.group(4) != null){
                processed[0] = "Insert";//operation
                processed[1] = matcher.group(5);//quantity
                processed[2] = matcher.group(6);// product
                processed[3] = matcher.group(7);//unit price
                processed[4] = "1";//message type
        
          }else if(matcher.group(8) != null){
                processed[0] = matcher.group(9); //operation
                processed[1] = "0"; // quantity
                processed[2] = matcher.group(11); // product
                processed[3] = matcher.group(10); //unit price
                processed[4] = "2";//message type
          }

      }else {
         System.out.println("NO MATCH");
      }
    
    
    return processed;
    } 
}
