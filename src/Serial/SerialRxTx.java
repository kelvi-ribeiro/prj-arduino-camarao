/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

/**
 *
 * @author lipec
 */
public class SerialRxTx implements SerialPortEventListener {

	SerialPort serialPort = null;

	private Protocolo protocolo = new Protocolo();// objeto de gestÃ£o de protocolo
	private String appName; // nome da aplicaÃ§Ã£o

	private BufferedReader input; // objeto para leitura na serial
	private OutputStream output; // objeto para escrita na serial

	private static final int TIME_OUT = 1000; // tempo de espera de comunicaÃ§Ã£o
	private static int DATA_RATE = 9600; // velocidade da porta serial

	private String serialPortName = "COM3";

	private static final String API_TEMPERATURA_URL = "https://c0476e16.ngrok.io/temperaturas";

	public boolean iniciaSerial() {
		boolean status = false;

		try {
			// obtÃ©m portas seriais do sistema
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

			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
			status = true;

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();

			}

		} catch (Exception e) {

			e.printStackTrace();
			status = false;

		}

		return status;
	}

	// MÃ©todo que envia dados pela serial.
	public void sendData(String data) {
		try {
			output = serialPort.getOutputStream();
			output.write(data.getBytes());
		} catch (Exception e) {

			System.err.println(e.toString());
			// retorna um erro, caso ocorra!
		}
	}

	// MÃ©todo que fecha a porta serial
	public synchronized void close() {

		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}

	}

	@Override
	public void serialEvent(SerialPortEvent spe) {
		// MÃ©todo para lidar com dados que chegam pela serial

		try {
			switch (spe.getEventType()) {
			case SerialPortEvent.DATA_AVAILABLE:
				if (input == null) {
					input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
				}

				if (input.ready()) {
					protocolo.setLeituraComando(input.readLine());
					if(protocolo.getLeituraComando() != null) {
						this.send(Double.valueOf(protocolo.getLeituraComando()).doubleValue());
						//System.out.println("temperatura: " + Double.valueOf(protocolo.getLeituraComando()).doubleValue());
					}
				}
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void send(double temperatura) throws JSONException {
		// ATENÇÃO,Isso é pra coloca logo abaixo a definição da classe, como qualquer
		// outro atributo de classe
		// Esse atributo aqui -> private static final String API_TEMPERATURA_URL = "
		// https://330f6520.ngrok.io/temperaturas";

		HttpClient client = HttpClientBuilder.create().build();

		HttpPost post = new HttpPost(API_TEMPERATURA_URL);
		post.setHeader("Content-type", "application/json");

		JSONObject message = new JSONObject();
		message.put("temperatura", temperatura);

		post.setEntity(new StringEntity(message.toString(), "UTF-8"));
		HttpResponse response = null;
		try {
			response = client.execute(post);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
