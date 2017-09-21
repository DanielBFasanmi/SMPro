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
 * @author Livinvg
 */
public class RegExer {
    public static void main(String message) {
     String line = message;
      String pattern = "^(([a-zA-Z0-9_']+) at (\\d+)p)$|(^(\\d+) sales of ([a-zA-Z0-9_']+) at (\\d+)p) each$|^((Add|Subtract|Multiply) (\\d+)p ([a-zA-Z0-9_']+))$";

      // Create a Pattern object
      Pattern r = Pattern.compile(pattern);

      // Now create matcher object.
      Matcher matcher = r.matcher(line);
      if (matcher.find()) {     
          System.out.println("1: "+matcher.group(1));
          System.out.println("2: "+matcher.group(2));
          System.out.println("3: "+matcher.group(3));
          System.out.println("4: "+matcher.group(4));
          System.out.println("5: "+matcher.group(5));
          System.out.println("6: "+matcher.group(6));
          System.out.println("7: "+matcher.group(7));
          System.out.println("8: "+matcher.group(8));
          System.out.println("9: "+matcher.group(9));
          System.out.println("10: "+matcher.group(10));
          System.out.println("11: "+matcher.group(11));
          System.out.println("-------------------------------------");
          
    }else {
          
         System.out.print("NO MATCH");
         System.out.println(" <--------- >"+message);
      }
    }
}
