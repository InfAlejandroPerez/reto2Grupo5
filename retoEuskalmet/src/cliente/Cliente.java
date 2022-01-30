package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



public class Cliente {
		
	
	private final int puerto =4444;
	private final String IP= "127.0.0.1";
	
	public void iniciar() {
		Socket cliente = null;
		ObjectInputStream entrada = null;
		ObjectOutputStream salida =null;
		
		try {
			 cliente = new Socket(IP,puerto);
			
			System.out.println("Conexión establecida con el servidor");
			salida = new ObjectOutputStream(cliente.getOutputStream());
			entrada = new ObjectInputStream(cliente.getInputStream());
			salida.writeObject("Hola, servidor");
			String linea = (String) entrada.readObject();
			System.out.println("Recibido: "+ linea);
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	public static void main(String[] args) {
		Cliente c = new Cliente();
		c.iniciar();
	}

}