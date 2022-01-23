package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

	private final int PUERTO = 5000;
	private final String IP = "localhost";

	public void inicar() {

		Socket cliente = null;
		ObjectInputStream entrada = null;
		ObjectOutputStream salida = null;

		try {
			// necesitamos una IP y un PUERTO para establecer la comunicacion
			cliente = new Socket(IP, PUERTO);
			System.out.println("Conexión establecida con el servidor");
			salida = new ObjectOutputStream(cliente.getOutputStream());
			entrada = new ObjectInputStream(cliente.getInputStream());
			// envio de informacion al servidor
			salida.writeObject("Hola servidor, soy un cliente");

			// recibo la respuesta del servidor
			String linea = (String) entrada.readObject();

			System.out.println("Recibido: " + linea);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {

				if (cliente != null)
					cliente.close();
				if (entrada != null)
					entrada.close();
				if (salida != null)
					salida.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		cliente.inicar();

	}

}
