/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpro;

/**
 *
 * @author 1412625
 */
public class Sale {
    private String productName;
    private double total;
    private int quantity;   

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double price, Operation operation) {
        double x = 0;
        switch (operation) {
            case ADD:
                x += price * quantity;
                if(x >0){
                    total +=x;
                }
                break;
                    
            case SUBTRACT:
                 x = price * quantity;
                if(x >0 && (total > x)){
                    total -=x;
                }
                break;
                         
            case MULTIPLY:
               x = total / quantity;
               total = x * price * quantity;            
                break;
                        
            default:

                break;
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
