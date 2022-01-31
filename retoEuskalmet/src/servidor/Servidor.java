package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;


class Servidor {

	public void iniciar() {
		
		ServerSocket servidor = null;
		int puerto = 4444;
		Socket cliente = null;
		ObjectInputStream entrada = null;
		ObjectOutputStream salida = null;
		String message = "";

		String operacion = null;
		String[] params = null;

		try {

			servidor = new ServerSocket(puerto);
			System.out.println("Servidor iniciado");

			while (!servidor.isClosed()) {

				cliente = servidor.accept();

				entrada = new ObjectInputStream(cliente.getInputStream());
				salida = new ObjectOutputStream(cliente.getOutputStream());
				System.out.println("Cliente conectado");

				message = (String) entrada.readObject();

				operacion = message.split("~")[0];
				
				if(message.split("~").length > 1) {
					params = message.split("~")[1].split(",");
				}
				

				switch (operacion) {
				case "login":
					salida.writeObject(ControladorServidor.checkLogin(params[0], params[1]));
					break;
				case "registroComprobacion":
					salida.writeObject(ControladorServidor.consultaRegistro(params[0], params[1]));
					break;
				case "registro":
									ControladorServidor.insertarDatosRegistro(params[0], params[1]);
					break;
				case "datosMunicipio":
					salida.writeObject(ControladorServidor.getDatosMunicipio(params[0]));
					break;
				case "datosEstacion":
					salida.writeObject(ControladorServidor.getDatosDiariosEstacion(params[0]));
					break;
				case "datosEspacioNatural":
					salida.writeObject(ControladorServidor.getDatosEspacioNatural(params[0]));
					break;
				case "datosHorarios":
					salida.writeObject(ControladorServidor.getDatosHorarios());
					break;
				case "listaMunicipios":
					salida.writeObject(ControladorServidor.getListaMuncipios(params[0]));
					break;
				case "listaEspacios":
					salida.writeObject(ControladorServidor.getEspaciosNaturales());
					break;
				case "estacionesMunicipio":
					salida.writeObject(ControladorServidor.getEstacionesMunicipio(params[0]));
					break;
				case "espaciosMunicipio":
					salida.writeObject(ControladorServidor.getEspaciosNaturalesMunicipio(params[0]));
					break;
				case "municipioOfEspacio":
					salida.writeObject(ControladorServidor.getMunicipioOfEspacio(params[0]));
					break;
				case "topMunicipios":
					salida.writeObject(ControladorServidor.getTopMunicipios());
					break;
				case "topMunicipiosProvincia":
					salida.writeObject(ControladorServidor.getTopMunicipiosProvincia(params[0]));
					break;
				}

				entrada.close();
				salida.close();
				cliente.close();

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			try {
				if (servidor != null)
					servidor.close();
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

		Servidor s = new Servidor();
		s.iniciar();
		
	}
}