/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smpro;

/**
 *
 * @author 1412625
 * 
 * the object of this class keeps each object sent by the terminal each (external company)
 * @param (messageType) is an holder for the type of message sent (type 1 or 2), 
 * a type 1 messages indicates that sales transaction are made
 * type 2 mesages indicate that an adjustment transaction is made
 */
public class Message {
    private String messageType, info;
    
    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
   public String toString(){
       return info;
   }
    
}
