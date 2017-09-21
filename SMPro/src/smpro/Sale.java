/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpro;

/**
 *
 * @author 1412625
 * the sales class object hold the information of all the sales made on a particular type of product
 */
public class Sale {
    private String productName; 
    private double total;
    private int quantity;   
    public Sale(){
    productName = "";
    total = 0.0;
    quantity = 0;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getTotal() {
        return total;
    }
/**
 * the setTotal method is a synchronized that locks on a 
 * thread and keeps other thread from invoking it
 * it is responsible for the processing of every possible transactional operation (add, subtract, multiply and insert) 
 * that can be performed.
 * 
 */
    public synchronized void setTotal(double price, Operation operation, int qty) {
        double x = 0;
        switch (operation) {
            case Add:
            // this method takes care of Addition 
                x = price * quantity;
                if(x >0){
                    total +=x;
                }
                break;
                    
            case Subtract:
                // this method takes care of subtraction
                 x = price * quantity;
                if(x >0 && (total > x)){
                    total -=x;
                }else{
                    total = 0.0;
                }
             
                break;
                         
            case Multiply:  
                // this method takes care of multiplication 
                if(quantity > 0){
               x = total / quantity; // to determine the initial price that was used (say we wanna double the price of a product, get the initial price, multiply it by the new price)
               total += x * price * quantity;    
         
                }
                break;
                        
            default:   
                // this methoid takes care of new sales.
                x += price * qty;
                if(x >0){
                    total +=x;
                }
                setQuantity(qty);
                break;
        }
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity += quantity;
    }
}
