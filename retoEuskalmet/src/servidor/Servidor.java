package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	// las variables finales van en mayús.
	private final int PUERTO = 5000;
	private int clientes = 0;
	private Socket cliente = null;
	private ObjectInputStream entrada = null;
	private ObjectOutputStream salida = null;

	public void iniciar() {
		// punto de comunicacion del servidor
		ServerSocket servidor = null;
		try {
			servidor = new ServerSocket(PUERTO);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println("Servicio iniciado| Esperando clientes...");

		try {
			while (true) {

				cliente = servidor.accept();
				entrada = new ObjectInputStream(cliente.getInputStream());

				salida = new ObjectOutputStream(cliente.getOutputStream());

				System.out.println("Esperando");

				String lineaLeer = (String) entrada.readObject();

				String[] mensaje = lineaLeer.split("/");

				accion(mensaje);

				System.out.println("Mensaje recibido: " + lineaLeer);

				salida.writeObject("Saludos desde el servidor al cliente nº" + clientes);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		// cuando manejamos ficheros y sockets es importante que nos cercioremos de que
		// se cierra todo

	}

	private void accion(String[] mensaje) throws IOException {
		switch (String.valueOf(mensaje[0])) {
		case "1":
			if (mensaje[1].equals("admin")) {
				salida.writeObject("Bienvenido " + mensaje[1]);
			} else
				//salida.writeObject("Usuario no encontrado");
			break;

		default:
			break;
		}

	}

	public static void main(String[] args) {

		Servidor servidor = new Servidor();
		servidor.iniciar();

	}

}