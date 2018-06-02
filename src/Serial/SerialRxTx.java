/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serial;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;

/**
 *
 * @author lipec
 */
public class SerialRxTx implements SerialPortEventListener {

    SerialPort serialPort = null;

    private Protocolo protocolo = new Protocolo();//objeto de gestão de protocolo
    private String appName; //nome da aplicação

    private BufferedReader input; //objeto para leitura na serial
    private OutputStream output; //objeto para escrita na serial

    private static final int TIME_OUT = 1000; //tempo de espera de comunicação
    private static int DATA_RATE = 9600; //velocidade da porta serial 

    private String serialPortName = "COM3";

    public boolean iniciaSerial() {
        boolean status = false;

        try {
            //obtém portas seriais do sistema
            CommPortIdentifier portId = null;
            Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

            while (portId == null && portEnum.hasMoreElements()) {
                CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();

                if (currPortId.getName().equals(serialPortName) || currPortId.getName().startsWith(serialPortName)) {
                    serialPort = (SerialPort) currPortId.open(appName, TIME_OUT);
                    portId = currPortId;
                    System.out.println("Conectado em: " + currPortId.getName());
                    break;
                }
            }

            if (portId == null || serialPort == null) {
                return false;
            }

            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
            status = true;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }

        } catch (Exception e) {

            e.printStackTrace();
            status = false;

        }

        return status;
    }
    
    //Método que envia dados pela serial.
    public void sendData(String data) {
        try {
            output = serialPort.getOutputStream();
            output.write(data.getBytes());
        } catch (Exception e) {

            System.err.println(e.toString());
            //retorna um erro, caso ocorra!
        }
    }

    //Método que fecha a porta serial
    public synchronized void close() {

        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }

    }

    @Override
    public void serialEvent(SerialPortEvent spe) {
        //Método para lidar com dados que chegam pela serial

        try {
            switch (spe.getEventType()) {
                case SerialPortEvent.DATA_AVAILABLE:
                    if (input == null) {
                        input = new BufferedReader(
                                new InputStreamReader(
                                        serialPort.getInputStream()));
                    }

                    if (input.ready()) {
                        protocolo.setLeituraComando(input.readLine());
                        
                        System.out.println("temperatura: " + Float.valueOf(protocolo.getLeituraComando()).floatValue());
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
    
    public static int getDATA_RATE() {
        return DATA_RATE;
    }

    public static void setDATA_RATE(int DATA_RATE) {
        SerialRxTx.DATA_RATE = DATA_RATE;
    }

    public String getSerialPortName() {
        return serialPortName;
    }

    public void setSerialPortName(String serialPortName) {
        this.serialPortName = serialPortName;
    }

}
