package servidor_cliente;

import java.net.Socket;

public class Cliente {

	private final int PUERTO = 5000;
	private final String IP = "192.168.56.1";

	public void inicar() {

		try {
			// necesitamos una IP y un PUERTO para establecer la comunicacion
			Socket cliente = new Socket(IP, PUERTO);
			System.out.println("Conexión establecida con el servidor");
			// TODO

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) {
		Cliente c = new Cliente();
		c.inicar();
	}

}
