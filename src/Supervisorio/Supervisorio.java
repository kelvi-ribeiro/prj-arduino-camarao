/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Supervisorio;


import Serial.SerialRxTx;

/**
 *
 * @author lipec
 */
public class Supervisorio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SerialRxTx serial = new SerialRxTx();
        
        if(serial.iniciaSerial()){
            while (true) {                
                
            }
        }
        else{
            
        }
    }
    
}
